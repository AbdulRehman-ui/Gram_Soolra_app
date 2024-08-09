package com.project.gramsoolra.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.project.gramsoolra.R
import com.project.gramsoolra.common.SharedPrefManager
import com.project.gramsoolra.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {

    private lateinit var binding : ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val appPreference = SharedPrefManager(this)
        val userId = appPreference.USER_ID

        appPreference.SALT = "Gramsootra@TT)(*&^!@2022"

        if (userId.isEmpty()) {
            Handler().postDelayed({
                startActivity(Intent(this@SplashScreen, LoginScreen::class.java))
                finish()
            },2000)
        }

        else
        {
            Handler().postDelayed({
                startActivity(Intent(this@SplashScreen, HomeScreen::class.java))
                finish()
            },2000)
        }


    }
}