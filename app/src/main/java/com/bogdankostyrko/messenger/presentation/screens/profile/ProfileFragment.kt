package com.bogdankostyrko.messenger.presentation.screens.profile

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import coil.load
import com.bogdankostyrko.data.messenger.network.api.ACTIVE_STATUS
import com.bogdankostyrko.data.messenger.network.api.MY_USER_ID
import com.bogdankostyrko.messenger.MessengerApp
import com.bogdankostyrko.messenger.R
import com.bogdankostyrko.messenger.databinding.FragmentProfileBinding
import com.bogdankostyrko.messenger.di.components.DaggerProfileComponent
import com.bogdankostyrko.messenger.domain.models.Status
import com.bogdankostyrko.messenger.presentation.activities.MainActivity
import com.bogdankostyrko.messenger.presentation.elm.ElmBaseFragment
import com.bogdankostyrko.messenger.presentation.elm.LceState
import com.bogdankostyrko.messenger.tools.setStatusBarColor
import com.github.terrakok.cicerone.Router
import vivid.money.elmslie.android.renderer.elmStoreWithRenderer
import vivid.money.elmslie.core.store.Store
import javax.inject.Inject

class ProfileFragment : ElmBaseFragment<
        ProfileEffect,
        ProfileState,
        ProfileEvent,
        >(R.layout.fragment_profile) {


    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var profileStoreFactory: ProfileStoreFactory

    @Inject
    lateinit var router: Router

    override val store: Store<ProfileEvent, ProfileEffect, ProfileState> by elmStoreWithRenderer(
        elmRenderer = this
    ) {
        profileStoreFactory.create()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val applicationComponent =
            (requireContext().applicationContext as MessengerApp).applicationComponent
        val profileComponent =
            DaggerProfileComponent.builder().applicationComponent(applicationComponent).build()
        profileComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)

        val userId = arguments?.getInt(USER_ID) ?: 0

        setActiveStatus()
        loadUser(userId)
        setToolbarVisibility(userId)
        setBottomNavVisibility(userId)
        initSwipeRefresh(userId)

        setStatusBarColor(
            activity = activity,
            context = requireContext(),
            colorResId = R.color.dark_grey_background
        )
    }

    private fun setActiveStatus() {
        store.accept(ProfileEvent.Ui.UpdateYourPresence(status = ACTIVE_STATUS))
    }

    private fun loadUser(userId: Int) {
        store.accept(ProfileEvent.Ui.LoadUser(userId))
    }

    private fun setToolbarVisibility(userId: Int) {
        if (userId != MY_USER_ID) {
            binding.apply {
                clToolbar.isVisible = true
                ivBackBtn.setOnClickListener {
                    store.accept(ProfileEvent.Ui.OnBackButtonClick)
                }
            }
        } else {
            binding.clToolbar.isGone = true
        }
    }

    override fun render(state: ProfileState) {
        processProfileState(state)
    }

    override fun handleEffect(effect: ProfileEffect) {
        when (effect) {

            ProfileEffect.NavigateToBack -> {
                router.exit()
            }

            ProfileEffect.ShowError -> {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun processProfileState(state: ProfileState) {
        when (val userToRender = state.user) {

            is LceState.Content -> {
                binding.apply {
                    ivAvatar.load(userToRender.content.avatarUrl)
                    tvName.text = userToRender.content.name

                    val statusColor = when (userToRender.content.status) {
                        Status.Active -> R.color.green_status_active
                        Status.Offline -> R.color.red_status_offline
                    }

                    tvStatus.apply {
                        text = userToRender.content.status.toString()
                        setTextColor(
                            ContextCompat.getColor(
                                requireContext(),
                                statusColor,
                            )
                        )
                    }
                    swipeRefreshLayout.isRefreshing = false
                }
            }

            LceState.Error -> {
                Toast.makeText(context, "User load error", Toast.LENGTH_SHORT).show()
                binding.swipeRefreshLayout.isRefreshing = false
            }

            LceState.Loading -> {
                binding.swipeRefreshLayout.isRefreshing = true
            }
        }
    }

    private fun setBottomNavVisibility(userId: Int) {
        if (userId != MY_USER_ID) {
            (activity as MainActivity).setBottomNavVisibility(isVisible = false)
        }
    }

    private fun initSwipeRefresh(userId: Int) {
        binding.swipeRefreshLayout.setOnRefreshListener {
            store.accept(ProfileEvent.Ui.LoadUser(userId = userId))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as MainActivity).setBottomNavVisibility(isVisible = true)
        _binding = null
    }

    companion object {

        private const val USER_ID = "userId"

        fun getInstance(userId: Int): ProfileFragment {
            return ProfileFragment().apply {
                arguments = Bundle().apply {
                    putInt(USER_ID, userId)
                }
            }
        }
    }
}