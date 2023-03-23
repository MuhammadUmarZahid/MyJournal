package com.bbx.myjournal

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bbx.myjournal.DatabaseConstants.EMOTION_TABLE
import com.bbx.myjournal.data.Emotion
import kotlinx.coroutines.flow.Flow


@Dao
interface EmotionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEmotion(emotionEntity: EmotionEntity)

    @Query("SELECT * FROM $EMOTION_TABLE ORDER BY created_at DESC")
    fun getAllEmotions(): Flow<List<EmotionEntity>>

    @Query("SELECT strftime('%Y-%m', created_at) as month, sum(emotion_type) as sum, count(*) as count FROM $EMOTION_TABLE GROUP BY month ORDER BY created_at DESC")
    fun getEmotionDataByMonth(): List<EmotionDataByMonthEntity>

    @Query("SELECT strftime('%d', created_at) as day, COUNT(*) as count FROM $EMOTION_TABLE WHERE strftime('%Y-%m', created_at) = :month GROUP BY strftime('%d', created_at) ORDER BY created_at DESC")
    fun getEmotionDataByDay(month: String?): List<EmotionDataByDayEntity>

    @Query("SELECT * FROM $EMOTION_TABLE WHERE strftime('%Y-%m-%d', created_at) = :date ORDER BY created_at DESC")
    fun getEmotionsForDate(date: String?): List<Emotion>
}
