package org.wordpress.android.ui.debug

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import org.wordpress.android.R
import org.wordpress.android.databinding.DebugSettingsFragmentBinding
import org.wordpress.android.ui.debug.DebugSettingsViewModel.NavigationAction.PreviewFragment
import org.wordpress.android.ui.debug.previews.PreviewFragmentActivity.Companion.previewFragmentInActivity
import org.wordpress.android.util.DisplayUtils
import org.wordpress.android.util.extensions.getSerializableCompat
import org.wordpress.android.viewmodel.observeEvent
import org.wordpress.android.widgets.RecyclerItemDecoration
import javax.inject.Inject

@AndroidEntryPoint
class DebugSettingsFragment : Fragment(R.layout.debug_settings_fragment) {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: DebugSettingsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[DebugSettingsViewModel::class.java]
        val adapter = DebugSettingsAdapter().setUpRecyclerView()

        with(viewModel) {
            uiState.observe(viewLifecycleOwner) {
                it?.let { uiState ->
                    adapter.submitList(uiState.uiItems)
                }
            }
            onNavigation.observeEvent(viewLifecycleOwner) {
                when (it) {
                    is PreviewFragment -> previewFragmentInActivity(it.name)
                    else -> Unit
                }
            }
            start(getDebugSettingsType())
        }
    }

    private fun getDebugSettingsType() = arguments?.getSerializableCompat<DebugSettingsType>(ARG_KEY) ?: error(
        "DebugSettingsType not provided"
    )

    private fun DebugSettingsAdapter.setUpRecyclerView() = also {
        with(DebugSettingsFragmentBinding.bind(requireView())) {
            recyclerView.addItemDecoration(RecyclerItemDecoration(0, DisplayUtils.dpToPx(requireActivity(), 1)))
            recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            recyclerView.adapter = this@setUpRecyclerView
        }
    }

    companion object {
        private const val ARG_KEY = "debug_settings_type_key"
        fun newInstance(debugSettingsType: DebugSettingsType) = DebugSettingsFragment().apply {
            arguments = Bundle().apply {
                putSerializable(ARG_KEY, debugSettingsType)
            }
        }
    }
}
