package com.example.ulybkaradugiapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ulybkaradugiapp.R
import com.example.ulybkaradugiapp.data.model.DocumentDetail
import com.example.ulybkaradugiapp.databinding.ItemDetailDocumentBinding

class DetailAdapter(private val listener: OnItemClickListener) :
    ListAdapter<DocumentDetail, DetailAdapter.DetailViewHolder>(DetailComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val binding =
            ItemDetailDocumentBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return DetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }

        holder.itemView.setOnClickListener {
            listener.onItemClick(currentItem)
        }
    }

    class DetailViewHolder(private val binding: ItemDetailDocumentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(detail: DocumentDetail) {
            binding.apply {

                if (detail.isReady) {
                    isReadyPoint.setImageResource(R.drawable.ic_baseline_check_circle_24)
                } else {
                    isReadyPoint.setImageResource(R.drawable.ic_baseline_check_circle_outline_24)
                }

                val textElementName = "Наименование элемента: ${detail.pos_name}"
                elementName.text = textElementName

                val textElementGroup = "Наименование группы: ${detail.pos_group_name}"
                elementGroup.text = textElementGroup

                val textElementCategory = "Наименование категории: ${detail.pos_category_name}"
                elementCategory.text = textElementCategory

                val textElementAmount = "Количество: ${detail.good_qty}"
                elementAmount.text = textElementAmount

                val textDocumentCategory = "Идентификатор документа: ${detail.id_hd_nakl}"
                documentId.text = textDocumentCategory
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(detail: DocumentDetail)
    }

    class DetailComparator : DiffUtil.ItemCallback<DocumentDetail>() {
        override fun areItemsTheSame(oldItem: DocumentDetail, newItem: DocumentDetail) =
            oldItem.id_good_nakl == newItem.id_good_nakl

        override fun areContentsTheSame(oldItem: DocumentDetail, newItem: DocumentDetail) =
            oldItem == newItem
    }
}