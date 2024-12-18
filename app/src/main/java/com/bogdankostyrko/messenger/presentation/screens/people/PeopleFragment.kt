package com.bogdankostyrko.messenger.presentation.screens.people

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bogdankostyrko.data.messenger.network.api.ACTIVE_STATUS
import com.bogdankostyrko.messenger.MessengerApp
import com.bogdankostyrko.messenger.R
import com.bogdankostyrko.messenger.databinding.FragmentPeopleBinding
import com.bogdankostyrko.messenger.di.components.DaggerPeopleComponent
import com.bogdankostyrko.messenger.domain.models.UserModel
import com.bogdankostyrko.messenger.presentation.adapters.itemdecoration.PeopleItemDecoration
import com.bogdankostyrko.messenger.presentation.adapters.users.UsersAdapter
import com.bogdankostyrko.messenger.presentation.elm.ElmBaseFragment
import com.bogdankostyrko.messenger.presentation.elm.LceState
import com.bogdankostyrko.messenger.presentation.navigation.Screens.Profile
import com.bogdankostyrko.messenger.tools.setStatusBarColor
import com.github.terrakok.cicerone.Router
import vivid.money.elmslie.android.renderer.elmStoreWithRenderer
import vivid.money.elmslie.core.store.Store
import javax.inject.Inject

class PeopleFragment : ElmBaseFragment<
        PeopleEffect,
        PeopleState,
        PeopleEvent,
        >(R.layout.fragment_people) {

    private var _binding: FragmentPeopleBinding? = null
    private val binding get() = _binding!!

    private val usersAdapter: UsersAdapter by lazy(LazyThreadSafetyMode.NONE) {
        UsersAdapter(::onUserItemClick)
    }

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var peopleStoreFactory: PeopleStoreFactory

    override val store: Store<PeopleEvent, PeopleEffect, PeopleState> by elmStoreWithRenderer(
        elmRenderer = this
    ) {
        peopleStoreFactory.create()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val applicationComponent =
            (requireContext().applicationContext as MessengerApp).applicationComponent
        val peopleComponent =
            DaggerPeopleComponent.builder().applicationComponent(applicationComponent).build()
        peopleComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPeopleBinding.bind(view)

        setActiveStatus()
        loadDataFirstTime(savedInstanceState)
        initAdapter()
        initSwipeRefresh()

        setStatusBarColor(
            activity = activity,
            context = requireContext(),
            colorResId = R.color.dark_grey_background
        )
    }

    override fun render(state: PeopleState) {
        processUsersState(state)
    }

    override fun handleEffect(effect: PeopleEffect) {
        when (effect) {

            is PeopleEffect.NavigateToUser -> {
                router.navigateTo(Profile(effect.user.userId))
            }

            PeopleEffect.ShowError -> {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setActiveStatus() {
        store.accept(PeopleEvent.Ui.UpdateYourPresence(status = ACTIVE_STATUS))
    }

    private fun loadDataFirstTime(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            store.accept(PeopleEvent.Ui.LoadAllUsers)
        }
    }

    private fun processUsersState(state: PeopleState) {
        when (val usersToRender = state.users) {

            is LceState.Content -> {
                usersAdapter.submitList(usersToRender.content)
                binding.swipeRefreshLayout.isRefreshing = false
            }

            LceState.Error -> {
                Toast.makeText(context, "Users load error", Toast.LENGTH_SHORT).show()
                binding.swipeRefreshLayout.isRefreshing = false
            }

            LceState.Loading -> {
                binding.swipeRefreshLayout.isRefreshing = true
            }
        }
    }

    private fun initAdapter() {
        binding.rvUsers.apply {
            adapter = usersAdapter
            addItemDecoration(
                PeopleItemDecoration(
                    firstTop = 25,
                    bottom = 17,
                    left = 15,
                    right = 15,
                    context = context,
                )
            )
        }
    }

    private fun initSwipeRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            store.accept(PeopleEvent.Ui.LoadAllUsers)
        }
    }

    private fun onUserItemClick(user: UserModel) {
        store.accept(PeopleEvent.Ui.OnUserItemClick(user))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}