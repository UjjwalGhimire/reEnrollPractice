package com.example.reenrollpractice

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.reenrollpractice.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
                              val sharedPreferences=this.getSharedPreferences(
                                  AppConstants.FILE_SHARED_PREF_LOGIN,
                                  Context.MODE_PRIVATE
                              )
                            val defaultValue=false

                            val isAlreadyLoggedIn=sharedPreferences.getBoolean(
                                AppConstants.KEY_IS_LOGGED_IN,
                                defaultValue
                            )

//                            val intent:Intent
//                            if(isAlreadyLoggedIn){
//                                 intent=Intent(this,MainActivity::class.java)
//                            }else{
//                                 intent=Intent(this,LoginActivity::class.java)
//                            }
//                            startActivity(intent)
//                            finish()
                            //we ge suggestion on above code and we optimize it further
                            val intent = if(isAlreadyLoggedIn){
                                Intent(this,MainActivity::class.java)
                                }else{
                                Intent(this,HomeActivity::class.java)
                                }
                            startActivity(intent)
                            finish()
        },5000)
    }
    
}