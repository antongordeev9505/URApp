package com.example.ulybkaradugiapp.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ulybkaradugiapp.R
import com.example.ulybkaradugiapp.other.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_documents.*

@AndroidEntryPoint
class DocumentsFragment : Fragment(R.layout.fragment_documents) {

    private val viewModel: DocumentsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = DocumentsAdapter()
        recycler_view.adapter = adapter

        viewModel.documents.observe(viewLifecycleOwner) { result ->
            adapter.submitList(result.data)

            progress_bar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
            progress_bar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
            text_view_error.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
            text_view_error.text = result.error?.localizedMessage
        }
    }
}