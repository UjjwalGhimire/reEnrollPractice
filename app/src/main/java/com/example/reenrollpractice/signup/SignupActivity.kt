package com.example.reenrollpractice.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.reenrollpractice.HomeActivity
import com.example.reenrollpractice.MainActivity
import com.example.reenrollpractice.R
import com.example.reenrollpractice.databinding.ActivitySignupBinding
import com.example.reenrollpractice.db.TestDatabase
import com.example.reenrollpractice.db.User

class SignupActivity : AppCompatActivity() {
    private  lateinit var signUpBinding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        signUpBinding= ActivitySignupBinding.inflate(layoutInflater)
        setContentView(signUpBinding.root)

        signUpBinding.backBtn.setOnClickListener{
            val intent= Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        signUpBinding.btnSignup.setOnClickListener{
            /*
            TODO Validation
            check if fields are empty
            email structure
            full name is input check space made or not
            passwprd and confirm password matacher
             */
            val fullName=signUpBinding.etFullname.text.toString().trim()
            val email=signUpBinding.etEmail.text.toString().trim()
            val password=signUpBinding.etPassword.text.toString().trim()
            val confirmPassword=signUpBinding.etConPassword.toString().trim()

            //after validation
            //val testDatabase=TestDatabase.getInstance(context:this)
            //this activity passes signup activity context but it makes problem
            //because after we signup, this activity destroys and TestDB.kt receives context from signup, leading to more memory uses and crash vulerabilities
            val testDatabase=TestDatabase.getInstance(applicationContext)
            val userDAO=testDatabase.getUserDAO()
            Thread{
                try {
                    val userInDB=userDAO.checkUserExist(email)
                    if(userInDB==null){
                        //insert into database
                        val user=User(fullName,email,password)
                        userDAO.insertNewUser(user)
                        clearInputFields()
                        showToast("New user added...")
                    }else{
                        runOnUiThread {
                            showToast("user already exist")

                        }
                    }

                }catch (exception:java.lang.Exception){
                    exception.printStackTrace()
                    runOnUiThread{
                        showToast("could not insert the user")
                        //Toast.makeText(this,"could not insert user",Toast.LENGTH_LONG).show()

                    }

                }
            }.start()



        }
//        //private fun showToast(msg:String){
//        fun showToast(msg:String){
//            Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
//        }


    }
    private fun showToast(msg:String){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
    }
    private fun clearInputFields(){
        signUpBinding.etEmail.text.clear()
        signUpBinding.etFullname.text.clear()
        signUpBinding.etPassword.text.clear()
        signUpBinding.etConPassword.text.clear()

    }
}