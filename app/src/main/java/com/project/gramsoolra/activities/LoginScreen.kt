package com.project.gramsoolra.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.project.gramsoolra.R
import com.project.gramsoolra.api.ApiClient
import com.project.gramsoolra.common.SharedPrefManager
import com.project.gramsoolra.common.Status
import com.project.gramsoolra.databinding.ActivityLoginScreenBinding
import com.project.gramsoolra.di.Utils
import com.project.gramsoolra.repositories.MainRepository
import com.project.gramsoolra.request.LoginRequest
import com.project.gramsoolra.viewModels.ApiViewModel
import com.project.gramsoolra.viewModels.ViewModelFactory
import java.security.MessageDigest

class LoginScreen : AppCompatActivity() {

    private lateinit var binding: ActivityLoginScreenBinding

    private val apiViewModel: ApiViewModel by viewModels() {
        ViewModelFactory(MainRepository(ApiClient.apiService))
    }

    private var userMobile = ""
    private var userPassword = ""
    private var authToken = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btLogin.setOnClickListener {

            if (validateUserInput()) {
                apiViewModel.login(prepareLoginRequest())
                loginAPI()
            }
        }
    }

    private fun validateUserInput(): Boolean {

        userMobile = binding.etMobile.text.toString().trim()
        userPassword = binding.etPassword.text.toString().trim()

        if (userMobile.isEmpty()) {
            Toast.makeText(this@LoginScreen, "Mobile Number is required", Toast.LENGTH_SHORT).show()
            return false
        }

        if (userMobile.length != 10) {
            Toast.makeText(
                this@LoginScreen,
                "Phone number must contain 10 digits",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        if (userPassword.isEmpty()) {
            Toast.makeText(this@LoginScreen, "Password is required", Toast.LENGTH_SHORT).show()
            return false
        }

        return true

    }

    private fun prepareLoginRequest(): LoginRequest {

        userMobile = binding.etMobile.text.toString().trim()
        userPassword = binding.etPassword.text.toString().trim()

        val appPreference = SharedPrefManager(this)
        val salt = appPreference.SALT

        val authTokenString = salt+userMobile+userPassword

        authToken = Utils.md5Hash(authTokenString)

        val loginRequest = LoginRequest()

        loginRequest.mobileNumber = userMobile
        loginRequest.password = userPassword
        loginRequest.authToken = authToken

        return loginRequest

    }


    private fun loginAPI() {

        apiViewModel.res_login.observe(this, Observer {
            when(it.status) {
                Status.SUCCESS -> {

                   val message = it.data?.success.toString()
                    if (message == "true") {

                        val appPreference = SharedPrefManager(this)
                        val userId = it.data?.parameters?.id.toString()
                        appPreference.USER_ID = userId

                        Toast.makeText(this@LoginScreen, ""+it.data?.message.toString(), Toast.LENGTH_SHORT).show()

                        startActivity(Intent(this@LoginScreen, HomeScreen::class.java))

                    }
                    else
                    {
                        binding.loginUI.visibility = View.VISIBLE
                        binding.loginProgressBar.visibility = View.GONE
                        Toast.makeText(this@LoginScreen, ""+it.data?.message.toString(), Toast.LENGTH_SHORT).show()
                    }



                }
                Status.LOADING -> {
                    binding.loginUI.visibility = View.GONE
                    binding.loginProgressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.loginUI.visibility = View.VISIBLE
                    binding.loginProgressBar.visibility = View.GONE
                }
            }
        })
    }

}