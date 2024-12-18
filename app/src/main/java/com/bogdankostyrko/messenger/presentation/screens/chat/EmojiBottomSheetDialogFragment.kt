package com.bogdankostyrko.messenger.presentation.screens.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.bogdankostyrko.messenger.databinding.BottomSheetDialogEmojiBinding
import com.bogdankostyrko.messenger.domain.models.ReactionModel
import com.bogdankostyrko.messenger.presentation.adapters.emoji.EmojiAdapter
import com.bogdankostyrko.messenger.presentation.screens.chat.ChatFragment.Companion.EMOJI_NAME
import com.bogdankostyrko.messenger.presentation.screens.chat.ChatFragment.Companion.EMOJI_REQUEST
import com.bogdankostyrko.messenger.presentation.screens.chat.ChatFragment.Companion.MESSAGE_ID
import com.bogdankostyrko.messenger.presentation.screens.chat.ChatFragment.Companion.MESSAGE_POSITION
import com.bogdankostyrko.messenger.tools.emojiSetNCU
import com.bogdankostyrko.messenger.tools.toReactionModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class EmojiBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private var _binding: BottomSheetDialogEmojiBinding? = null
    private val binding get() = _binding!!

    private val emojiAdapter: EmojiAdapter by lazy {
        EmojiAdapter { reactionModel ->
            onEmojiClick(reactionModel)
        }
    }

    private var messagePosition: Int? = null
    private var messageId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetDialogEmojiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setEmojiAdapter()
        messagePosition = arguments?.getInt(MESSAGE_POSITION)
        messageId = arguments?.getInt(MESSAGE_ID)
        emojiAdapter.submitList(emojiSetNCU.map { it.toReactionModel() })
    }

    private fun setEmojiAdapter() {
        binding.rvEmoji.adapter = emojiAdapter
    }

    private fun onEmojiClick(model: ReactionModel) {
        if (messagePosition != null) {

            setFragmentResult(
                EMOJI_REQUEST,
                bundleOf(
                    EMOJI_NAME to model.emojiName,
                    MESSAGE_POSITION to messagePosition,
                    MESSAGE_ID to messageId,
                )
            )
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun getNewInstance(messagePosition: Int, messageId: Int): EmojiBottomSheetDialogFragment {
            return EmojiBottomSheetDialogFragment().apply {
                arguments = Bundle().apply {
                    putInt(MESSAGE_POSITION, messagePosition)
                    putInt(MESSAGE_ID, messageId)
                }
            }
        }
    }
}