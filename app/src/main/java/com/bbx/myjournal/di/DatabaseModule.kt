package com.bbx.myjournal.di

import android.content.Context
import androidx.room.Room
import com.bbx.myjournal.data.local.DatabaseConstants.JOURNAL_DATABASE
import com.bbx.myjournal.data.local.EmotionDao
import com.bbx.myjournal.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
                context, AppDatabase::class.java, JOURNAL_DATABASE
                )
                .build()
    }

    @Provides
    @Singleton
    fun provideEmotionDao(appDatabase: AppDatabase): EmotionDao {
        return appDatabase.emotionDao()
    }


}