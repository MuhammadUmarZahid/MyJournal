package com.bbx.myjournal

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

        @Provides
        @Singleton
        fun bindEmotionsRepository(emotionDao: EmotionDao,dateTimeConverters: DateTimeConverters): EmotionsRepository{
            return DefaultEmotionsRepository(emotionDao,dateTimeConverters)
        }

}