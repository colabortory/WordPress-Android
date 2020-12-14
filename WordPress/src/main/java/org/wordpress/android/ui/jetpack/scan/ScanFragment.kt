package org.wordpress.android.ui.jetpack.scan

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.scan_fragment.*
import org.wordpress.android.R
import org.wordpress.android.R.drawable
import org.wordpress.android.WordPress
import org.wordpress.android.fluxc.model.SiteModel
import org.wordpress.android.ui.jetpack.scan.ScanViewModel.UiState.Content
import org.wordpress.android.ui.jetpack.scan.adapters.ScanAdapter
import org.wordpress.android.ui.utils.UiHelpers
import org.wordpress.android.util.AppLog
import org.wordpress.android.util.AppLog.T.SCAN
import javax.inject.Inject

class ScanFragment : Fragment(R.layout.scan_fragment) {
    @Inject lateinit var uiHelpers: UiHelpers
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: ScanViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDagger()
        initRecyclerView()
        initViewModel(getSite(savedInstanceState))
    }

    private fun initDagger() {
        (requireActivity().application as WordPress).component()?.inject(this)
    }

    private fun initRecyclerView() {
        initItemDecorator()
        initAdapter()
    }

    private fun initItemDecorator() {
        requireContext().getDrawable(drawable.default_list_divider)?.let {
            val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            itemDecorator.setDrawable(it)
            recycler_view.addItemDecoration(itemDecorator)
        } ?: AppLog.w(SCAN, "List divider null")
    }

    private fun initAdapter() {
        val scanAdapter = ScanAdapter(uiHelpers)
        scanAdapter.setHasStableIds(true)
        recycler_view.adapter = scanAdapter
        recycler_view.itemAnimator = null
    }

    private fun initViewModel(site: SiteModel) {
        viewModel = ViewModelProvider(this, viewModelFactory).get(ScanViewModel::class.java)
        setupObservers()
        viewModel.start(site)
    }

    private fun setupObservers() {
        viewModel.uiState.observe(
            viewLifecycleOwner,
            Observer { uiState ->
                if (uiState is Content) {
                    refreshContentScreen(uiState)
                }
            }
        )
    }

    private fun refreshContentScreen(content: Content) {
        ((recycler_view.adapter) as ScanAdapter).update(content.items)
    }

    private fun getSite(savedInstanceState: Bundle?): SiteModel {
        return if (savedInstanceState == null) {
            requireActivity().intent.getSerializableExtra(WordPress.SITE) as SiteModel
        } else {
            savedInstanceState.getSerializable(WordPress.SITE) as SiteModel
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable(WordPress.SITE, viewModel.site)
        super.onSaveInstanceState(outState)
    }
}
