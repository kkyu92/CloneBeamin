package com.clonebeamin.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.clonebeamin.data.local.token.Token
import com.clonebeamin.data.local.token.TokenDao

@Database(entities = [Token::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tokenDao(): TokenDao
}