package com.bogdankostyrko.messenger.presentation.adapters.channels.stream

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bogdankostyrko.messenger.databinding.StreamItemBinding
import com.bogdankostyrko.messenger.domain.models.StreamModel
import com.bogdankostyrko.messenger.presentation.adapters.delegate.AdapterDelegate
import com.bogdankostyrko.messenger.presentation.adapters.delegate.DelegateItem

class StreamAdapterDelegate(
    private val onStreamClick: (stream: StreamModel) -> Unit,
    private val onTopicButtonClick: (stream: StreamModel) -> Unit
) : AdapterDelegate {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder =
        StreamViewHolder(
            binding = StreamItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onStreamClick = onStreamClick,
            onTopicButtonClick = onTopicButtonClick,
        )

    override fun onBindViewHolder(
        holder: ViewHolder,
        delegateItem: DelegateItem,
        position: Int
    ) {
        (holder as StreamViewHolder).bind(
            streamModel = delegateItem.content() as StreamModel
        )
    }

    override fun isOfViewType(delegateItem: DelegateItem): Boolean =
        delegateItem is StreamDelegateItem

    class StreamViewHolder(
        private val binding: StreamItemBinding,
        private val onStreamClick: (stream: StreamModel) -> Unit,
        private val onTopicButtonClick: (stream: StreamModel) -> Unit
    ) : ViewHolder(binding.root) {

        fun bind(streamModel: StreamModel) {
            binding.tvStreamTitle.text = streamModel.streamName
            binding.main.setOnClickListener {
                onStreamClick(streamModel)
            }
            binding.ivStreamTopicsBtn.apply {
                setOnClickListener {
                    onTopicButtonClick(streamModel)
                }
                isSelected = streamModel.isSelected
            }
        }
    }
}