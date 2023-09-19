package com.example.reenrollpractice.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDAO {
    @Insert
    fun insertNewUser(user:User)

    @Delete
    fun deleteExistingUser(user: User)

    @Query(" Select * from user where email=:emailAddress ")
    fun checkUserExist(emailAddress:String):User?
    //the last one is return type, kotlin has return type at the last

    @Query("Select * from user where email= :userEmail and password= :userPassword" )
    fun checkValidUser(userEmail:String,userPassword:String):User?
}