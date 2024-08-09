package com.project.gramsoolra.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.project.gramsoolra.R
import com.project.gramsoolra.api.ApiClient
import com.project.gramsoolra.common.Status
import com.project.gramsoolra.databinding.ActivityLoginScreenBinding
import com.project.gramsoolra.repositories.MainRepository
import com.project.gramsoolra.request.LoginRequest
import com.project.gramsoolra.viewModels.ApiViewModel
import com.project.gramsoolra.viewModels.ViewModelFactory

class LoginScreen : AppCompatActivity() {

    private lateinit var binding : ActivityLoginScreenBinding

    private val apiViewModel: ApiViewModel by viewModels() {
        ViewModelFactory(MainRepository(ApiClient.apiService))
    }

    private var userMobile = ""
    private var userPassword = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btLogin.setOnClickListener {

            userMobile = "9876543210"
            userPassword = "12345678"

            val loginRequest = LoginRequest()

            loginRequest.mobileNumber = userMobile
            loginRequest.password = userPassword
            loginRequest.authToken = "jkbfjkdasbgajgbsdjkgn"

            apiViewModel.login(loginRequest)
            loginAPI()

            startActivity(Intent(this@LoginScreen, HomeScreen::class.java))
        }
    }

    private fun loginAPI() {

        apiViewModel.res_login.observe(this, Observer {
            when(it.status) {
                Status.SUCCESS -> {
                   val message = it.data?.message.toString()
                    Toast.makeText(this@LoginScreen, ""+message, Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> {

                }
                Status.ERROR -> {
                    val message = it.data?.message.toString()
                    Toast.makeText(this@LoginScreen, ""+message, Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

}