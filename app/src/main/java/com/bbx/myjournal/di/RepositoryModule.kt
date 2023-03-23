package com.bbx.myjournal.di

import com.bbx.myjournal.data.DefaultEmotionsRepository
import com.bbx.myjournal.data.EmotionsRepository
import com.bbx.myjournal.data.local.EmotionDao
import com.bbx.myjournal.utils.DateTimeConverters
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

        @Provides
        @Singleton
        fun bindEmotionsRepository(emotionDao: EmotionDao, dateTimeConverters: DateTimeConverters): EmotionsRepository {
            return DefaultEmotionsRepository(emotionDao,dateTimeConverters)
        }

}