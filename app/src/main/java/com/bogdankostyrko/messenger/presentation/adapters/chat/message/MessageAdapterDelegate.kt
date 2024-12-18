package com.bogdankostyrko.messenger.presentation.adapters.chat.message

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bogdankostyrko.messenger.databinding.MessageItemBinding
import com.bogdankostyrko.messenger.domain.models.MessageModel
import com.bogdankostyrko.messenger.presentation.adapters.delegate.AdapterDelegate
import com.bogdankostyrko.messenger.presentation.adapters.delegate.DelegateItem

class MessageAdapterDelegate(

    private val onMessageLongClick: (
        position: Int,
        messageId: Int,
    ) -> Unit,

    private val onEmojiClick: (
        emojiName: String,
        messageId: Int,
    ) -> Unit,

    private val onAddEmojiClick: (
        messageId: Int,
        position: Int,
    ) -> Unit

) : AdapterDelegate {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder =
        MessageViewHolder(
            binding = MessageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onMessageLongClick = onMessageLongClick,
            onEmojiClick = onEmojiClick,
            onAddEmojiClick = onAddEmojiClick,
        )

    override fun onBindViewHolder(
        holder: ViewHolder,
        delegateItem: DelegateItem,
        position: Int,
    ) {
        (holder as MessageViewHolder).bind(
            messageModel = delegateItem.content() as MessageModel,
            position = position,
        )
    }

    override fun isOfViewType(delegateItem: DelegateItem): Boolean =
        delegateItem is MessageDelegateItem

    class MessageViewHolder(
        private val binding: MessageItemBinding,

        private val onMessageLongClick: (
            messagePosition: Int,
            messageId: Int,
        ) -> Unit,

        private val onEmojiClick: (
            emojiName: String,
            messageId: Int,
        ) -> Unit,

        private val onAddEmojiClick: (
            messageId: Int,
            position: Int,
        ) -> Unit
    ) :
        ViewHolder(binding.root) {

        fun bind(messageModel: MessageModel, position: Int) {

            binding.vgMessage.setMessage(
                messageModel = messageModel,
                position = position,
                onMessageLongClick = onMessageLongClick,
                onEmojiClick = onEmojiClick,
                onAddEmojiClick = onAddEmojiClick,
            )
        }
    }
}