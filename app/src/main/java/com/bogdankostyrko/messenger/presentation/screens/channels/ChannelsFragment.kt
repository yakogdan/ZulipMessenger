package com.bogdankostyrko.messenger.presentation.screens.channels

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bogdankostyrko.data.messenger.network.api.ACTIVE_STATUS
import com.bogdankostyrko.messenger.MessengerApp
import com.bogdankostyrko.messenger.R
import com.bogdankostyrko.messenger.databinding.FragmentChannelsBinding
import com.bogdankostyrko.messenger.di.components.DaggerChannelsComponent
import com.bogdankostyrko.messenger.presentation.adapters.channels.ChannelsAdapter
import com.bogdankostyrko.messenger.presentation.adapters.channels.shimmer.ShimmerAdapterDelegate
import com.bogdankostyrko.messenger.presentation.adapters.channels.stream.StreamAdapterDelegate
import com.bogdankostyrko.messenger.presentation.adapters.channels.topic.TopicAdapterDelegate
import com.bogdankostyrko.messenger.presentation.adapters.delegate.DelegateItem
import com.bogdankostyrko.messenger.presentation.elm.ElmBaseFragment
import com.bogdankostyrko.messenger.presentation.elm.LceState
import com.bogdankostyrko.messenger.presentation.navigation.Screens.Chat
import com.bogdankostyrko.messenger.tools.setStatusBarColor
import com.github.terrakok.cicerone.Router
import com.google.android.material.tabs.TabLayout
import vivid.money.elmslie.android.renderer.elmStoreWithRenderer
import vivid.money.elmslie.core.store.Store
import javax.inject.Inject

class ChannelsFragment : ElmBaseFragment<
        ChannelsEffect,
        ChannelsState,
        ChannelsEvent,
        >(R.layout.fragment_channels) {

    private var _binding: FragmentChannelsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var channelsStoreFactory: ChannelsStoreFactory

    override val store: Store<ChannelsEvent, ChannelsEffect, ChannelsState> by elmStoreWithRenderer(
        elmRenderer = this
    ) {
        channelsStoreFactory.create()
    }

    private val channelsAdapter: ChannelsAdapter by lazy(LazyThreadSafetyMode.NONE) {
        ChannelsAdapter().apply {
            addDelegates()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val applicationComponent =
            (requireContext().applicationContext as MessengerApp).applicationComponent
        val channelsComponent =
            DaggerChannelsComponent.builder().applicationComponent(applicationComponent).build()
        channelsComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentChannelsBinding.bind(view)

        setStatusBarColor(
            activity = activity,
            context = requireContext(),
            colorResId = R.color.dark_grey_background
        )

        setActiveStatus()
        initTab()
        initAdapter()
        initSwipeRefresh()
    }

    override fun render(state: ChannelsState) {
        processChannelsState(state.channels)
    }

    override fun handleEffect(effect: ChannelsEffect) {
        when (effect) {

            is ChannelsEffect.NavigateToChat -> {
                router.navigateTo(
                    Chat(
                        streamName = effect.streamName,
                        topicName = effect.topicName,
                    )
                )
            }

            ChannelsEffect.ShowError -> {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setActiveStatus() {
        store.accept(ChannelsEvent.Ui.UpdateYourPresence(status = ACTIVE_STATUS))
    }

    private fun initTab() {

        binding.tlStreams.addTab(
            binding.tlStreams.newTab().setText(getString(R.string.subscribed))
        )
        binding.tlStreams.addTab(
            binding.tlStreams.newTab().setText(getString(R.string.all_streams))
        )

        binding.tlStreams.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {

                    SUBSCRIBED_TAB_INDEX -> {
                        clearSearchStreamsEditText()
                        store.accept(ChannelsEvent.Ui.OnSubscribedStreamsTabClick)
                    }

                    ALL_STREAMS_TAB_INDEX -> {
                        clearSearchStreamsEditText()
                        store.accept(ChannelsEvent.Ui.OnAllStreamsTabClick)
                    }

                    else -> return
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) = Unit
            override fun onTabReselected(tab: TabLayout.Tab?) = Unit
        })
    }

    private fun initAdapter() {
        binding.rvChannels.adapter = channelsAdapter
        store.accept(ChannelsEvent.Ui.LoadSubscribedStreams)
    }

    private fun initSwipeRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            if (store.states.value.isSubscribedStreams) {
                store.accept(ChannelsEvent.Ui.LoadSubscribedStreams)
            } else {
                store.accept(ChannelsEvent.Ui.LoadAllStreams)
            }
        }
    }

    private fun ChannelsAdapter.addDelegates() {
        addDelegate(
            StreamAdapterDelegate(

                onStreamClick = { stream ->
                    store.accept(
                        ChannelsEvent.Ui.OnStreamItemClick(
                            streamName = stream.streamName,
                        )
                    )
                },

                onTopicButtonClick = { stream ->
                    store.accept(
                        ChannelsEvent.Ui.OnStreamTopicsButtonClick(
                            stream = stream,
                        )
                    )
                },
            )
        )

        addDelegate(
            TopicAdapterDelegate(
                onTopicClick = { topic ->
                    store.accept(
                        ChannelsEvent.Ui.OnTopicItemClick(
                            topic = topic,
                        )
                    )
                },
            )
        )

        addDelegate(ShimmerAdapterDelegate())
    }

    private fun processChannelsState(state: LceState<List<DelegateItem>>) {
        when (state) {

            LceState.Error -> {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                binding.swipeRefreshLayout.isRefreshing = false
            }

            LceState.Loading -> {
                binding.swipeRefreshLayout.isRefreshing = true
            }

            is LceState.Content -> {
                channelsAdapter.submitList(state.content)
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    private fun clearSearchStreamsEditText() {
        val editText = binding.etSearchStreams
        if (editText.text.isNotEmpty()) {
            editText.setText("")
        }
        editText.clearFocus()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val SUBSCRIBED_TAB_INDEX = 0
        const val ALL_STREAMS_TAB_INDEX = 1
    }
}