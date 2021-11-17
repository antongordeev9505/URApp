package com.example.ulybkaradugiapp.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.ulybkaradugiapp.R
import com.example.ulybkaradugiapp.other.Resource
import com.example.ulybkaradugiapp.ui.DetailAdapter
import com.example.ulybkaradugiapp.ui.DividerItemDecoration
import com.example.ulybkaradugiapp.ui.DocumentsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_documents.*

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val adapter = DetailAdapter()
    private val viewModel: DocumentsViewModel by viewModels()
    private val args by navArgs<DetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view_detail.adapter = adapter

        observeResult()
        setDividerItemDecoration()
    }

    private fun observeResult() {
        val idDocument = args.idDocument
        viewModel.getDetails(idDocument).observe(viewLifecycleOwner) { details ->
            adapter.submitList(details.data)

            progress_bar_detail.isVisible = details is Resource.Loading && details.data.isNullOrEmpty()
            progress_bar_detail.isVisible = details is Resource.Loading && details.data.isNullOrEmpty()
            text_view_error_detail.isVisible = details is Resource.Error && details.data.isNullOrEmpty()
            text_view_error_detail.text = details.error?.localizedMessage
        }
    }

    private fun setDividerItemDecoration() {
        val heightInPixels = resources.getDimensionPixelSize(R.dimen.list_item_divider_height)
        context?.let {
            recycler_view_detail.addItemDecoration(DividerItemDecoration(ContextCompat.getColor(it, R.color.black), heightInPixels))
        }
    }
}