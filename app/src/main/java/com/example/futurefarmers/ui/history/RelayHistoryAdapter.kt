package com.example.futurefarmers.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.futurefarmers.data.response.RecordsItem
import com.example.futurefarmers.databinding.LayoutHistoryBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class RelayHistoryAdapter :
    PagingDataAdapter<RecordsItem, RelayHistoryAdapter.RelayHistoryViewHolder>(RELAY_HISTORY_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelayHistoryViewHolder {
        val binding = LayoutHistoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RelayHistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RelayHistoryViewHolder, position: Int) {
        val recordsItem = getItem(position)
        if (recordsItem != null) {
            holder.bind(recordsItem)
        }
    }

    class RelayHistoryViewHolder(private val binding: LayoutHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RecordsItem) {
            binding.apply {
                // Bind your data here
                val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
                inputFormat.timeZone = TimeZone.getTimeZone("GMT+7")
                val outputFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())

                val date: Date? = inputFormat.parse(item.createdAt)
                val formattedDate: String = date?.let { outputFormat.format(it) }.toString()

                tvTgl.text = formattedDate
                tvLabel.text = item.type
                tvOnOff.text = item.status
            }
        }
    }

    companion object {
        private val RELAY_HISTORY_COMPARATOR = object : DiffUtil.ItemCallback<RecordsItem>() {
            override fun areItemsTheSame(oldItem: RecordsItem, newItem: RecordsItem): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: RecordsItem, newItem: RecordsItem): Boolean =
                oldItem == newItem
        }
    }
}