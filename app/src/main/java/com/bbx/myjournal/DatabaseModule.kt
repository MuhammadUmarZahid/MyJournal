package com.bbx.myjournal

import android.content.Context
import androidx.room.Room
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.bbx.myjournal.DatabaseConstants.JOURNAL_DATABASE
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