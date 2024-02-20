package org.wordpress.android.ui.notifications.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.wordpress.android.databinding.NotificationsListItemBinding
import org.wordpress.android.datasets.NotificationsTable
import org.wordpress.android.models.Note
import org.wordpress.android.ui.notifications.NotificationsListViewModel.InlineActionEvent
import org.wordpress.android.util.extensions.indexOrNull

class NotesAdapter(private val inlineActionEvents: MutableSharedFlow<InlineActionEvent>) :
    RecyclerView.Adapter<NoteViewHolder>() {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private var reloadLocalNotesJob: Job? = null
    val filteredNotes = ArrayList<Note>()
    var onNoteClicked = { _: String -> }
    var onNotesLoaded = { _: Int -> }
    var onScrolledToBottom = { _: Long -> }
    var currentFilter = Filter.ALL
        private set

    init {
        // this is on purpose - we don't show more than a hundred or so notifications at a time so no need to set
        // stable IDs. This helps prevent crashes in case a note comes with no ID (we've code checking for that
        // elsewhere, but telling the RecyclerView.Adapter the notes have stable Ids and then failing to provide them
        // will make things go south as in https://github.com/wordpress-mobile/WordPress-Android/issues/8741
        setHasStableIds(false)
    }

    fun setFilter(newFilter: Filter) {
        currentFilter = newFilter
    }

    /**
     * Add notes to the adapter and notify the change
     */
    fun addAll(notes: List<Note>) = coroutineScope.launch {
        withContext(Dispatchers.Main) {
            val newNotes = buildFilteredNotesList(notes, currentFilter)
            val differ = AsyncListDiffer(this@NotesAdapter, object : ItemCallback<Note>() {
                override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean =
                    oldItem.id == newItem.id

                override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean =
                    oldItem.json.toString() == newItem.json.toString()
            })

            filteredNotes.clear()
            filteredNotes.addAll(newNotes)

            differ.submitList(newNotes)
            onNotesLoaded(itemCount)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder =
        NoteViewHolder(
            NotificationsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            inlineActionEvents,
            coroutineScope
        )

    override fun getItemCount(): Int = filteredNotes.size

    override fun onBindViewHolder(noteViewHolder: NoteViewHolder, position: Int) {
        val note = filteredNotes.getOrNull(position) ?: return
        val previousNote = filteredNotes.getOrNull(position - 1)

        with(noteViewHolder) {
            bindTimeGroupHeader(note, previousNote, position)
            bindSubject(note)
            bindSubjectNoticon(note)
            bindContent(note)
            bindAvatars(note)
            bindInlineActions(note)
            bindOthers(note, onNoteClicked)
        }

        // request to load more comments when we near the end
        if (position >= itemCount - 1) {
            onScrolledToBottom(note.timestamp)
        }
    }

    fun cancelReloadLocalNotes() {
        reloadLocalNotesJob?.cancel()
    }

    /**
     * Reload the notes from local database and update the adapter
     */
    fun reloadLocalNotes() {
        cancelReloadLocalNotes()
        reloadLocalNotesJob = coroutineScope.launch {
            addAll(NotificationsTable.getLatestNotes())
        }
    }

    /**
     * Update the note in the adapter and notify the change
     */
    fun updateNote(note: Note) {
        filteredNotes.indexOrNull { it.id == note.id }?.let { notePosition ->
            filteredNotes[notePosition] = note
            notifyItemChanged(notePosition)
        }
    }

    companion object {
        // Instead of building the filtered notes list dynamically, create it once and re-use it.
        // Otherwise it's re-created so many times during layout.
        @JvmStatic
        fun buildFilteredNotesList(
            notes: List<Note>,
            filter: Filter
        ): ArrayList<Note> = notes.filter { note ->
            when (filter) {
                Filter.ALL -> true
                Filter.COMMENT -> note.isCommentType
                Filter.FOLLOW -> note.isFollowType
                Filter.UNREAD -> note.isUnread
                Filter.LIKE -> note.isLikeType
            }
        }.sortedByDescending { it.timestamp }.let { result -> ArrayList(result) }
    }
}

enum class Filter(val value: String) {
    ALL("all"),
    COMMENT("comment"),
    FOLLOW("follow"),
    LIKE("like"),
    UNREAD("unread");
}
