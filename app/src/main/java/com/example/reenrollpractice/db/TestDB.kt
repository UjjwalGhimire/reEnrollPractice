package com.example.reenrollpractice.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(entities = [User::class], version = 1)
abstract class TestDatabase:RoomDatabase() {
    abstract fun getUserDAO(): UserDAO

    companion object {
        private const val DB_Name = "test.db"

        @Volatile
        private var INSTANCE: TestDatabase? = null

        fun getInstance(context: Context): TestDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    TestDatabase::class.java,
                    DB_Name
                ).build()
            }
            return INSTANCE!!
        }
    }
}