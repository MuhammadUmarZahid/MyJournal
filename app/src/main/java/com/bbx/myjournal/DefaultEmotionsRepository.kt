package com.bbx.myjournal

import com.bbx.myjournal.data.Emotion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import java.util.*
import javax.inject.Inject

class DefaultEmotionsRepository @Inject constructor(private val emotionDao: EmotionDao,private val dateTimeConverters: DateTimeConverters):EmotionsRepository {

    override suspend fun createEmotion(notes:String, emotionType:Int) {
        val date = Date(System.currentTimeMillis())
        val emotionEntity = EmotionEntity(emotionNotes = notes, emotionType = emotionType, createdAt = date)
        return emotionDao.insertEmotion(emotionEntity)
    }

    override fun getEmotions(): Flow<List<EmotionModel>> {
        return emotionDao.getAllEmotions().map {
            it.map {
                it.toDomain()
            }
        }
    }

    override fun getEmotionsGroupedByMonthDay(): Flow<List<EmotionDataByMonthModel>> {
        return flow<List<EmotionDataByMonthModel>> {
            val emotionDataByMonthList: List<EmotionDataByMonthModel> = emotionDao.getEmotionDataByMonth().map {
                it.toModel()
            }
            for (emotionDataByMonth in emotionDataByMonthList) {
                val month: String = emotionDataByMonth.month ?: ""
                val days: List<EmotionDataByDayModel> = emotionDao.getEmotionDataByDay(month).map {
                    it.toModel()
                }
                    emotionDataByMonth.month = dateTimeConverters.getMonthName(month)
                    emotionDataByMonth.days = days
                for (emotionDataByDay in days) {
                    val date = month + "-" + emotionDataByDay.day
                    emotionDataByDay.date = dateTimeConverters.getDayFormat(date)
                    val emotions: List<Emotion> = emotionDao.getEmotionsForDate(date)
                    emotionDataByDay.emotions = emotions.map {
                        it.copy(
                            time =dateTimeConverters.convertToTime(it.created_at?:Date())?:""
                        )
                    }
                }
            }
            emit(emotionDataByMonthList)
        }.flowOn(Dispatchers.IO)
    }

}