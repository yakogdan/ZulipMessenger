package com.bogdankostyrko.messenger.presentation.adapters.channels.shimmer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bogdankostyrko.messenger.databinding.StreamShimmerItemBinding
import com.bogdankostyrko.messenger.presentation.adapters.delegate.AdapterDelegate
import com.bogdankostyrko.messenger.presentation.adapters.delegate.DelegateItem

class ShimmerAdapterDelegate : AdapterDelegate {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder =
        ShimmerViewHolder(
            StreamShimmerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
        )

    override fun onBindViewHolder(
        holder: ViewHolder,
        delegateItem: DelegateItem,
        position: Int
    ) {
        (holder as ShimmerViewHolder).bind()
    }

    override fun isOfViewType(delegateItem: DelegateItem): Boolean =
        delegateItem is ShimmerDelegateItem

    class ShimmerViewHolder(
        binding: StreamShimmerItemBinding
    ) : ViewHolder(binding.root) {
        fun bind() {}
    }
}