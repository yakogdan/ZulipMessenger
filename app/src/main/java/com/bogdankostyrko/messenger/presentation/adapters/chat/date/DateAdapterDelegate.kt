package com.bogdankostyrko.messenger.presentation.adapters.chat.date

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bogdankostyrko.messenger.databinding.DateItemBinding
import com.bogdankostyrko.messenger.domain.models.DateModel
import com.bogdankostyrko.messenger.domain.models.toDayAndMonth
import com.bogdankostyrko.messenger.presentation.adapters.delegate.AdapterDelegate
import com.bogdankostyrko.messenger.presentation.adapters.delegate.DelegateItem

class DateAdapterDelegate : AdapterDelegate {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder =
        DateViewHolder(
            DateItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(
        holder: ViewHolder,
        delegateItem: DelegateItem,
        position: Int
    ) {
        (holder as DateViewHolder).bind(delegateItem.content() as DateModel)
    }

    override fun isOfViewType(delegateItem: DelegateItem): Boolean =
        delegateItem is DateDelegateItem

    class DateViewHolder(private val binding: DateItemBinding) : ViewHolder(binding.root) {

        fun bind(dateModel: DateModel) {
            binding.tvDate.text = dateModel.toDayAndMonth()
        }
    }
}