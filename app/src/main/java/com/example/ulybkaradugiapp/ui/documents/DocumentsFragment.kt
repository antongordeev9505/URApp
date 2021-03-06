package com.example.ulybkaradugiapp.ui.documents

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ulybkaradugiapp.R
import com.example.ulybkaradugiapp.data.model.ApiDocument
import com.example.ulybkaradugiapp.other.Resource
import com.example.ulybkaradugiapp.ui.DividerItemDecoration
import com.example.ulybkaradugiapp.ui.DocumentsAdapter
import com.example.ulybkaradugiapp.ui.DocumentsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_documents.*

@AndroidEntryPoint
class DocumentsFragment : Fragment(R.layout.fragment_documents), DocumentsAdapter.OnItemClickListener{

    private val adapter = DocumentsAdapter(this)
    private val viewModel: DocumentsViewModel by viewModels()
    private var documentsList: Resource<List<ApiDocument>>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.adapter = adapter
        setDividerItemDecoration()
        observeResult()

        setHasOptionsMenu(true)
    }

    private fun observeResult() {
        viewModel.documents.observe(viewLifecycleOwner) { result ->
            documentsList = result
            adapter.submitList(result.data)

            progress_bar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
            progress_bar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
            text_view_error.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
            text_view_error.text = result.error?.localizedMessage
        }
    }

    private fun updateDocuments() {
        viewModel.reloadList(true)
    }

    private fun cacheDetails() {
        documentsList?.data?.map { it.id_record }?.forEach {
            viewModel.getDetailsToStore(it)
        }
    }

    private fun setDividerItemDecoration() {
        val heightInPixels = resources.getDimensionPixelSize(R.dimen.list_item_divider_height)
        context?.let {
            recycler_view.addItemDecoration(DividerItemDecoration(ContextCompat.getColor(it, R.color.black), heightInPixels))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_documents, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.reload_list -> {
                updateDocuments()
                Toast.makeText(activity, "???????????? ????????????????", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.cache_details -> {
                cacheDetails()
                Toast.makeText(activity, "?????? ???????????? ???????????????????? ??????????????", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onItemClick(idDocument: Int) {
        val action = DocumentsFragmentDirections.actionDocumentsFragmentToDetailFragment(idDocument)
        findNavController().navigate(action)
    }
}