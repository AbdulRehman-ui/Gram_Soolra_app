package com.project.gramsoolra.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
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

    private val apiViewModel : ApiViewModel by viewModels() {
        ViewModelFactory(MainRepository(ApiClient.apiService))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        cartAPIRequest()
        bottomNavData()


    }

    private fun bottomNavData() {
        val navView: BottomNavigationView = binding.navView

        val tvHeader = binding.tvHeader

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

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

    private fun getCartAPI() {
        apiViewModel.res_cart_list.observe(this) {

            when(it.status) {

                Status.SUCCESS -> {

                    val totalItems = it.data?.parameters?.data?.size.toString()

                   binding.totalCartItems.text = totalItems

                }

                Status.LOADING -> {

                }

                Status.ERROR -> {
                }
            }
        }
    }

}