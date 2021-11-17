package com.example.ulybkaradugiapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ulybkaradugiapp.data.model.ApiDocument
import com.example.ulybkaradugiapp.databinding.ItemDocumentBinding

class DocumentsAdapter(private val listener: OnItemClickListener) :
    ListAdapter<ApiDocument, DocumentsAdapter.DocumentViewHolder>(DocumentComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocumentViewHolder {
        val binding =
            ItemDocumentBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return DocumentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DocumentViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }

        holder.itemView.setOnClickListener {
            listener.onItemClick(currentItem.id_record)
        }
    }

    class DocumentViewHolder(private val binding: ItemDocumentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(document: ApiDocument) {
            binding.apply {
                indexNumber.text = document.id_pos.toString()

                val textDocumentId = "Идентификатор документа: ${document.id_record}"
                documentId.text = textDocumentId

                val textDocumentGroup = "Наименование группы документа: ${document.nom_route}"
                documentGroup.text = textDocumentGroup

                val textNumberOfOrder = "Текстовый номер заказа: ${document.nom_zak}"
                numberOfOrder.text = textNumberOfOrder
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(idDocument: Int)
    }

    class DocumentComparator : DiffUtil.ItemCallback<ApiDocument>() {
        override fun areItemsTheSame(oldItem: ApiDocument, newItem: ApiDocument) =
            oldItem.id_record == newItem.id_record

        override fun areContentsTheSame(oldItem: ApiDocument, newItem: ApiDocument) =
            oldItem == newItem
    }
}