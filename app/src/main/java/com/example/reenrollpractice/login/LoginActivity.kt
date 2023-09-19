package com.example.reenrollpractice.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.reenrollpractice.*
import com.example.reenrollpractice.db.TestDatabase
import com.example.reenrollpractice.db.User
import com.google.android.material.floatingactionbutton.FloatingActionButton

class LoginActivity : AppCompatActivity() {
    private  val tag: String ="LoginActivity as constant var" //val is like constant and var is alike variable
    private val a="Next activity" // we do not need to specify the type, so type inference
    private val b=2

    private lateinit var e:EditText
    private lateinit var p:EditText
    private lateinit var btnlogin:Button
    private lateinit var btnback:FloatingActionButton
//    private lateinit var btnlogin:Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
//        Log.i is for info
//        Log.e is for error message
//        Log.d is for debug
        Log.i("LoginActivity","now we are in onCreate activity");
        Log.i(tag,"now we are in onCreate activity");
        e=findViewById(R.id.emailval)
        p=findViewById(R.id.passlval)
        btnlogin=findViewById(R.id.login)
        btnback=findViewById(R.id.lg_backbtn)

        btnback.setOnClickListener{
            val intent=Intent(this,HomeActivity::class.java)
            startActivity(intent)
            finish()

        }

        btnlogin.setOnClickListener(){
            Log.i("buttonLogin","login button is pressed")
            var eval=e.text.toString()
            var pval=p.text.toString()
            Log.i("email value of the form",eval)
            Log.i("password value of the form",pval)
            if(eval.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(eval).matches()){
                Toast.makeText(this, "Email is empty or error", Toast.LENGTH_SHORT).show()
            }//else if (pval.isBlank() || !isValidPassword(pval))
            else if (pval.isBlank() ){
                Toast.makeText(applicationContext, "Enter a password", Toast.LENGTH_SHORT).show()
            }else{
//                Todo local or remote authentication
                val SharedPreferences=this.getSharedPreferences(
                    AppConstants.FILE_SHARED_PREF_LOGIN,
                    Context.MODE_PRIVATE
                )
                val sharedPrefEditor=SharedPreferences.edit()
                sharedPrefEditor.putBoolean(
                    AppConstants.KEY_IS_LOGGED_IN,true
                )
                sharedPrefEditor.apply()

//Checking from database to make login
                val testDatabase=TestDatabase.getInstance(applicationContext)
                val userDAO=testDatabase.getUserDAO()
                Thread{
                   try {
                       val userInDB=userDAO.checkValidUser(eval,pval)
                       if(userInDB==null){
                            runOnUiThread{
                                showToast("Email or Password is incorrect")
                            }
                       }else{
                           runOnUiThread {
                               showToast("Logged In successfully")
                               onSuccessfulLogin(userInDB)
                           }


                       }
                   }catch (exception:Exception){
                       exception.printStackTrace()
                       runOnUiThread {
                           showToast("Could not log in please Try again later")
                       }
                   }
                }.start()


            }



        }


        btnlogin.text="Hi It's me, Click Me";

    }

//    public boolean isValidPassword(final String password) {
//
//        Pattern pattern;
//        Matcher matcher;
//
//        val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
//
//        pattern = Pattern.compile(PASSWORD_PATTERN);
//        matcher = pattern.matcher(password);
//
//        return matcher.matches();
//
//    }
    override fun onStart() {
        super.onStart()
        Log.i(tag,"now we are in onStart activity");
    }

    override fun onResume() {
        super.onResume()
        Log.i(tag,"now we are in onResumeactivity");
    }

    override fun onPause() {
        super.onPause()
        Log.i(tag,"now we are in onPause activity");
    }

    override fun onStop() {
        super.onStop()
        Log.i(tag,"now we are in onStopactivity");
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(tag,"now we are in onDestroy activity");
    }
    private fun showToast(message: String){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }
    private fun onSuccessfulLogin(user:User){
        //val loginData= Login(eval,pval)
        //we stop using this and delete this

        val intent=Intent(this, MainActivity::class.java)
        // intent.putExtra("enteredEmail",eval)
//        intent.putExtra(AppConstants.KEY_EMAIL,eval)
//
//        intent.putExtra("name","ujjwal")
//        intent.putExtra(AppConstants.KEY_PASSWORD,pval)
//
//        intent.putExtra(AppConstants.KEY_LOGIN_DATA,loginData)
        //we also stop sending data and discountinue and delete these

        intent.putExtra(
            AppConstants.KEY_LOGIN_DATA,
            user
        )


        startActivity(intent)


//                registerForActivityResult()
        finish()
    }
}