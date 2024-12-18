package com.bogdankostyrko.messenger.presentation.adapters.emoji

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bogdankostyrko.messenger.databinding.EmojiDialogItemBinding
import com.bogdankostyrko.messenger.domain.models.ReactionModel

class EmojiAdapter(
    private val onClick: (reaction: ReactionModel) -> Unit
) : ListAdapter<ReactionModel, EmojiAdapter.EmojiViewHolder>(
    AsyncDifferConfig.Builder(EmojiDiffUtil()).build()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmojiViewHolder {
        return EmojiViewHolder(
            EmojiDialogItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClick
        )
    }

    override fun onBindViewHolder(holder: EmojiViewHolder, position: Int) {
        val emoji = getItem(position)
        holder.bind(emoji)
    }

    class EmojiViewHolder(
        private val binding: EmojiDialogItemBinding,
        val onClick: (reaction: ReactionModel) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(reaction: ReactionModel) {
            binding.tvEmojiDialog.text = reaction.emojiCode
            binding.root.setOnClickListener {
                onClick(reaction)
            }
        }
    }

    class EmojiDiffUtil : DiffUtil.ItemCallback<ReactionModel>() {
        override fun areItemsTheSame(oldItem: ReactionModel, newItem: ReactionModel): Boolean {
            return oldItem.emojiCode == newItem.emojiCode
        }

        override fun areContentsTheSame(oldItem: ReactionModel, newItem: ReactionModel): Boolean {
            return oldItem == newItem
        }
    }
}