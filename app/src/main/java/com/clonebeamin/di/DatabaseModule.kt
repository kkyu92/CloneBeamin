package com.clonebeamin.di

import android.content.Context
import androidx.room.Room
import com.clonebeamin.data.local.AppDatabase
import com.clonebeamin.data.local.token.TokenDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "database"
        ).build()
    }

    @Provides
    fun provideTokenDao(appDatabase: AppDatabase) : TokenDao {
        return appDatabase.tokenDao()
    }
}