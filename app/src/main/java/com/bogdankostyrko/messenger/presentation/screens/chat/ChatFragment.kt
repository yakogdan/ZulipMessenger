package com.bogdankostyrko.messenger.presentation.screens.chat

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import com.bogdankostyrko.data.messenger.network.api.ACTIVE_STATUS
import com.bogdankostyrko.messenger.MessengerApp
import com.bogdankostyrko.messenger.R
import com.bogdankostyrko.messenger.databinding.FragmentChatBinding
import com.bogdankostyrko.messenger.di.components.DaggerChatComponent
import com.bogdankostyrko.messenger.presentation.activities.MainActivity
import com.bogdankostyrko.messenger.presentation.adapters.chat.ChatAdapter
import com.bogdankostyrko.messenger.presentation.adapters.chat.date.DateAdapterDelegate
import com.bogdankostyrko.messenger.presentation.adapters.chat.message.MessageAdapterDelegate
import com.bogdankostyrko.messenger.presentation.adapters.delegate.DelegateItem
import com.bogdankostyrko.messenger.presentation.adapters.itemdecoration.ChatItemDecoration
import com.bogdankostyrko.messenger.presentation.elm.ElmBaseFragment
import com.bogdankostyrko.messenger.presentation.elm.LceState
import com.bogdankostyrko.messenger.tools.setStatusBarColor
import com.github.terrakok.cicerone.Router
import vivid.money.elmslie.android.renderer.elmStoreWithRenderer
import vivid.money.elmslie.core.store.Store
import javax.inject.Inject

class ChatFragment : ElmBaseFragment<
        ChatEffect,
        ChatState,
        ChatEvent,
        >(R.layout.fragment_chat) {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    private val chatAdapter: ChatAdapter by lazy(LazyThreadSafetyMode.NONE) {
        ChatAdapter().apply {
            addDelegates()
        }
    }

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var chatStoreFactory: ChatStoreFactory

    override val store: Store<ChatEvent, ChatEffect, ChatState> by elmStoreWithRenderer(
        elmRenderer = this
    ) {
        chatStoreFactory.create()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val applicationComponent =
            (requireContext().applicationContext as MessengerApp).applicationComponent
        val chatComponent =
            DaggerChatComponent.builder().applicationComponent(applicationComponent).build()
        chatComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentChatBinding.bind(view)

        val streamName = arguments?.getString(STREAM_NAME) ?: ""
        val topicName = arguments?.getString(TOPIC_NAME)

        (activity as MainActivity).setBottomNavVisibility(isVisible = false)

        setActiveStatus()

        loadDataFirstTime(
            streamName = streamName,
            topicName = topicName,
            savedInstanceState = savedInstanceState,
        )

        if (savedInstanceState == null) {
            store.accept(
                ChatEvent.Ui.SubscriptionOnEvents(
                    streamName = streamName,
                    topicName = topicName,
                )
            )
        }

        initAdapter()

        setSendButtonClickListener(
            streamName = streamName,
            topicName = topicName
        )

        setFragmentResultListener(EMOJI_REQUEST) { _, bundle ->
            bundle.getString(EMOJI_NAME)?.let { emojiName ->
                onReactionClick(
                    emojiName = emojiName,
                    messageId = bundle.getInt(MESSAGE_ID)
                )
            }
        }

        setStatusBarColor(
            activity = activity,
            context = requireContext(),
            colorResId = R.color.mint
        )
    }

    override fun render(state: ChatState) {
        processMessagesState(state.messages)
    }

    override fun handleEffect(effect: ChatEffect) {
        when (effect) {

            ChatEffect.NavigateToBack -> {
                router.exit()
            }

            ChatEffect.ShowError -> {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadDataFirstTime(
        streamName: String,
        topicName: String?,
        savedInstanceState: Bundle?
    ) {
        if (savedInstanceState == null) {
            store.accept(
                ChatEvent.Ui.LoadMessages(
                    streamName = streamName,
                    topicName = topicName,
                )
            )
        }
    }

    private fun processMessagesState(state: LceState<List<DelegateItem>>) {
        when (state) {

            is LceState.Content -> {
                chatAdapter.submitList(state.content.reversed())
            }

            LceState.Error -> {
                Toast.makeText(context, "Error loading messages", Toast.LENGTH_SHORT).show()
            }

            LceState.Loading -> {
                Toast.makeText(context, "Loading messages", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setSendButtonClickListener(
        streamName: String,
        topicName: String?,
    ) {
        binding.btnSendMessage.setOnClickListener {
            val messageText = binding.etWriteMsg.text
            if (messageText.isNotEmpty()) {

                store.accept(
                    ChatEvent.Ui.OnSendButtonClick(
                        streamName = streamName,
                        messageText = messageText.toString(),
                        topicName = topicName ?: getString(R.string.default_topic_name),
                    )
                )

                binding.etWriteMsg.apply {
                    text.clear()
                }
            }
        }
    }

    private fun setActiveStatus() {
        store.accept(ChatEvent.Ui.UpdateYourPresence(status = ACTIVE_STATUS))
    }

    private fun onReactionClick(
        messageId: Int,
        emojiName: String,
    ) {
        store.accept(
            ChatEvent.Ui.OnReactionClick(
                messageId = messageId,
                emojiName = emojiName,
            )
        )
    }

    private fun showBottomSheetDialog(messagePosition: Int, messageId: Int) {
        EmojiBottomSheetDialogFragment
            .getNewInstance(
                messagePosition = messagePosition,
                messageId = messageId,
            )
            .show(
                parentFragmentManager,
                BOTTOM_SHEET_DIALOG_TAG
            )
    }

    private fun initAdapter() {
        binding.rvMessage.adapter = chatAdapter
        binding.rvMessage.addItemDecoration(
            ChatItemDecoration(
                padding = 20,
                context = requireContext(),
            )
        )
    }

    private fun ChatAdapter.addDelegates() {
        addDelegate(DateAdapterDelegate())
        addDelegate(
            MessageAdapterDelegate(
                onMessageLongClick = { position, messageId ->
                    showBottomSheetDialog(
                        messagePosition = position,
                        messageId = messageId,
                    )
                },

                onEmojiClick = { emojiName, messageId ->
                    onReactionClick(
                        messageId = messageId,
                        emojiName = emojiName,
                    )
                },

                onAddEmojiClick = { messageId, position ->
                    showBottomSheetDialog(
                        messagePosition = position,
                        messageId = messageId,
                    )
                }
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as MainActivity).setBottomNavVisibility(isVisible = true)
        _binding = null
    }

    companion object {

        private const val BOTTOM_SHEET_DIALOG_TAG = "emojiBottomSheetDialog"
        const val MESSAGE_POSITION = "messagePosition"
        const val MESSAGE_ID = "messageId"
        const val EMOJI_NAME = "emojiName"
        const val EMOJI_REQUEST = "emojiRequest"

        private const val STREAM_NAME = "streamName"
        private const val TOPIC_NAME = "topicName"

        fun getInstance(
            streamName: String,
            topicName: String? = null,
        ): ChatFragment {
            return ChatFragment().apply {
                arguments = Bundle().apply {

                    putString(STREAM_NAME, streamName)

                    if (topicName != null) {
                        putString(TOPIC_NAME, topicName)
                    }
                }
            }
        }
    }
}