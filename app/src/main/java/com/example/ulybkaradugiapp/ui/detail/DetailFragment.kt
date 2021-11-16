package com.example.ulybkaradugiapp.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ulybkaradugiapp.R
import com.example.ulybkaradugiapp.ui.DetailAdapter
import com.example.ulybkaradugiapp.ui.DocumentsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detail.*

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val adapter = DetailAdapter()
    private val viewModel: DocumentsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view_detail.adapter = adapter

        viewModel.details.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}