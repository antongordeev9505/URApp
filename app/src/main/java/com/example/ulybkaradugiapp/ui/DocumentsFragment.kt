package com.example.ulybkaradugiapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ulybkaradugiapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_documents.*

@AndroidEntryPoint
class DocumentsFragment : Fragment(R.layout.fragment_documents) {

    private val viewModel: DocumentsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = DocumentsAdapter()
        recycler_view.adapter = adapter

        viewModel.documents.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}