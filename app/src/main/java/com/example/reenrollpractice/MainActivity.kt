package com.example.reenrollpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.reenrollpractice.db.User

class MainActivity : AppCompatActivity() {
    private  val tag ="DashboardActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val receivedintent=intent
        //val receivedemail=intent.getStringExtra("enteredEmail")
//        val receivedemail=intent.getStringExtra(AppConstants.KEY_EMAIL)
//        val receivedname=intent.getStringExtra("name")
//        val receivedpassword=intent.getStringExtra(AppConstants.KEY_PASSWORD)
        //We also do not need it now because we do not need single data, we get object of User


        //val receivedLoginData=receivedintent.getParcelableExtra<Login>(AppConstants.KEY_LOGIN_DATA)
        //After we implement room database and send from login on receiving part we change Login
        //to User
        val receivedLoginData=receivedintent.getParcelableExtra<User>(AppConstants.KEY_LOGIN_DATA)

//        Log.i(tag, "Received Email ".plus(receivedemail))
//        Log.i(tag, "Received Name ".plus(receivedname))
//        Log.i(tag, "Received Password ".plus(receivedpassword))
        //we also remove it

        Log.i(tag, "Received Email ".plus(receivedLoginData?.email))
        Log.i(tag, "Received Password ".plus(receivedLoginData?.password))



    }



}