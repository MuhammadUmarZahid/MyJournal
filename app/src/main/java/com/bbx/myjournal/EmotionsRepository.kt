package com.bbx.myjournal

import kotlinx.coroutines.flow.Flow

interface EmotionsRepository {

    suspend fun createEmotion(notes:String, emotionType:Int)

    fun getEmotions(): Flow<List<EmotionModel>>

    fun getEmotionsGroupedByMonthDay(): Flow<List<EmotionDataByMonthModel>>

}