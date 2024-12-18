package com.bogdankostyrko.messenger.presentation.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bogdankostyrko.data.messenger.network.api.MY_USER_ID
import com.bogdankostyrko.messenger.MessengerApp
import com.bogdankostyrko.messenger.R
import com.bogdankostyrko.messenger.databinding.ActivityMainBinding
import com.bogdankostyrko.messenger.presentation.navigation.Screens.Channels
import com.bogdankostyrko.messenger.presentation.navigation.Screens.People
import com.bogdankostyrko.messenger.presentation.navigation.Screens.Profile
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator = AppNavigator(this, R.id.fragment_container)

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as MessengerApp).applicationComponent.inject(this)
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            router.newRootScreen(Channels())
        }

        initBottomNav()
    }

    private fun initBottomNav() {
        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_channels -> router.newRootScreen(Channels())

                R.id.menu_people -> router.newRootScreen(People())

                R.id.menu_profile -> router.newRootScreen(Profile(MY_USER_ID))

                else -> error("Unknown navigation tab")
            }

            return@setOnItemSelectedListener true
        }
    }

    fun setBottomNavVisibility(isVisible: Boolean) {
        if (isVisible) {
            binding.bottomNavigation.visibility = View.VISIBLE
        } else {
            binding.bottomNavigation.visibility = View.GONE
        }
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}