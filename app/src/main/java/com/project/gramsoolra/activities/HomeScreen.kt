package com.project.gramsoolra.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.OptIn
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.ExperimentalBadgeUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.project.gramsoolra.R
import com.project.gramsoolra.api.ApiClient
import com.project.gramsoolra.common.SharedPrefManager
import com.project.gramsoolra.common.Status
import com.project.gramsoolra.databinding.ActivityHomeScreenBinding
import com.project.gramsoolra.di.Utils
import com.project.gramsoolra.repositories.MainRepository
import com.project.gramsoolra.request.ProductListRequest
import com.project.gramsoolra.viewModels.ApiViewModel
import com.project.gramsoolra.viewModels.ViewModelFactory


class HomeScreen : AppCompatActivity() {

    private lateinit var binding : ActivityHomeScreenBinding

    private var totalCartItems = 0

    private val apiViewModel : ApiViewModel by viewModels() {
        ViewModelFactory(MainRepository(ApiClient.apiService))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        cartAPIRequest()
        bottomNavData(totalCartItems)

        binding.backButton.setOnClickListener {
            onBackPressed()
        }

    }

    private fun cartAPIRequest() {
        val appPreference = SharedPrefManager(this@HomeScreen)

        val userId = appPreference.USER_ID
        val salt = appPreference.SALT

        val authToken = Utils.md5Hash(salt + userId)

        val cartListRequest = ProductListRequest()

        cartListRequest.userId = userId
        cartListRequest.authToken = authToken

        apiViewModel.cartList(cartListRequest)
        getCartAPI()

    }

    @OptIn(ExperimentalBadgeUtils::class)
    private fun getCartAPI() {
        apiViewModel.res_cart_list.observe(this) {

            when(it.status) {

                Status.SUCCESS -> {

                    totalCartItems = it.data?.parameters?.data?.size!!
                    binding.totalCartItems.text = totalCartItems.toString()

                    bottomNavData(totalCartItems)
                }

                Status.LOADING -> {

                }

                Status.ERROR -> {
                }
            }
        }
    }

    private fun bottomNavData(cartItemCount : Int) {
        val navView: BottomNavigationView = binding.navView

        val tvHeader = binding.tvHeader

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

        val badge: BadgeDrawable = binding.navView.getOrCreateBadge(R.id.nav_cart)
        badge.isVisible = true
        badge.number = cartItemCount

        badge.backgroundColor = ContextCompat.getColor(this, R.color.primary_color)
        badge.badgeTextColor = ContextCompat.getColor(this, R.color.white)
        badge.maxCharacterCount = 2

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.nav_home -> {
                    tvHeader.text = getString(R.string.gramsootra)
                }
                R.id.nav_search -> {
                    tvHeader.text = getString(R.string.search)
                }
                R.id.nav_cart -> {
                    tvHeader.text = getString(R.string.my_cart)
                }

                R.id.nav_profile -> {
                    tvHeader.text = getString(R.string.profile)
                }

                R.id.nav_menu -> {
                    tvHeader.text = getString(R.string.menu)
                }
            }
        }

    }

    override fun onBackPressed() {

        if (true) {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
        else
        {
            super.onBackPressed()
        }

    }


}