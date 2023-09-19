package com.example.reenrollpractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.reenrollpractice.databinding.ActivityHomeBinding
import com.example.reenrollpractice.login.LoginActivity
import com.example.reenrollpractice.signup.SignupActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var homeActivityHomeBinding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeActivityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeActivityHomeBinding.root)
        homeActivityHomeBinding.btnLogin.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        homeActivityHomeBinding.btnSignup.setOnClickListener{
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}