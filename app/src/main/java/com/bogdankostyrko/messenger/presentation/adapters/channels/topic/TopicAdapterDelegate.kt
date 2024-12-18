package com.bogdankostyrko.messenger.presentation.adapters.channels.topic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bogdankostyrko.messenger.databinding.TopicItemBinding
import com.bogdankostyrko.messenger.domain.models.TopicModel
import com.bogdankostyrko.messenger.presentation.adapters.delegate.AdapterDelegate
import com.bogdankostyrko.messenger.presentation.adapters.delegate.DelegateItem

class TopicAdapterDelegate(
    private val onTopicClick: (topic: TopicModel) -> Unit
) : AdapterDelegate {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder =
        TopicViewHolder(
            binding = TopicItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
            onTopicClick = onTopicClick,
        )

    override fun onBindViewHolder(
        holder: ViewHolder,
        delegateItem: DelegateItem,
        position: Int
    ) {
        (holder as TopicViewHolder).bind(topic = delegateItem.content() as TopicModel)
    }

    override fun isOfViewType(delegateItem: DelegateItem): Boolean =
        delegateItem is TopicDelegateItem

    class TopicViewHolder(
        private val binding: TopicItemBinding,
        private val onTopicClick: (topic: TopicModel) -> Unit
    ) : ViewHolder(binding.root) {

        fun bind(topic: TopicModel) {
            binding.tvTopicTitle.text = topic.topicName
            binding.main.setOnClickListener {
                onTopicClick(topic)
            }
        }
    }
}