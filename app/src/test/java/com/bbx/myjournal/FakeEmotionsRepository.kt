package com.bbx.myjournal

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.*
import kotlin.collections.ArrayList

class FakeEmotionsRepository:EmotionsRepository {
    val emotionGroupByMonthDayEntity = arrayListOf<EmotionDataByMonthEntity>()
    val emotionGroupByDayEntity = arrayListOf<EmotionDataByDayEntity>()
    val emotionList = arrayListOf<Emotion>()

    val emotionItems = arrayListOf<EmotionEntity>()

    val dateTimeConverters:DateTimeConverters = DateTimeConverters()


    override suspend fun createEmotion(notes: String, emotionType: Int) {
        emotionItems.add(EmotionEntity(emotionItems.size,emotionType,notes, createdAt = Date(System.currentTimeMillis())))
    }

    override fun getEmotions(): Flow<List<EmotionModel>> {
       return flow<List<EmotionModel>>{
           val list =emotionItems.map {
               it.toDomain()
           }
           emit(list)
       }.flowOn(Dispatchers.IO)
    }

    override fun getEmotionsGroupedByMonthDay(): Flow<List<EmotionDataByMonthModel>> {
        return flow<List<EmotionDataByMonthModel>> {
            val list = arrayListOf<Emotion>()
            list.add(Emotion(2,1,"testing", created_at = Date(1558764780000),
            time="11:13"))
            val daylist = arrayListOf<EmotionDataByDayModel>()
            daylist.add(EmotionDataByDayModel(23,2, emotions = list, date = "Thu, 23"))
            val monthlist = arrayListOf<EmotionDataByMonthModel>()
            monthlist.add(EmotionDataByMonthModel("May",2,1, days =daylist))
            emit(monthlist)
        }.flowOn(Dispatchers.IO)
    }


    private fun getEmotionDataByMonth(): List<EmotionDataByMonthModel> {
        emotionGroupByMonthDayEntity.add(EmotionDataByMonthEntity("2019-05",4,5))
        return emotionGroupByMonthDayEntity.map {
            it.toModel()
        }
    }

    private fun getEmotionDataByDay(): List<EmotionDataByDayModel> {
        emotionGroupByDayEntity.add(EmotionDataByDayEntity(day=25,1))
        return emotionGroupByDayEntity.map {
            it.toModel()
        }
    }

    private fun getEmotionsList(): ArrayList<Emotion> {
        emotionList.add(Emotion(0,1,"neutral", Date(1558764780000)))
        return emotionList
    }

    fun getTestData(): List<EmotionDataByMonthModel> {
        val emotionDataByMonthList: List<EmotionDataByMonthModel> = getEmotionDataByMonth()
        for (emotionDataByMonth in emotionDataByMonthList) {
            val month: String = emotionDataByMonth.month ?: ""
            emotionDataByMonth.month = dateTimeConverters.getMonthName(month)
            val days: List<EmotionDataByDayModel> = getEmotionDataByDay()
            emotionDataByMonth.days = days
            for (emotionDataByDay in days) {
                val date = month + "-" + emotionDataByDay.day
                emotionDataByDay.date = dateTimeConverters.getDayFormat(date)
                val emotions: List<Emotion> = getEmotionsList()
                emotionDataByDay.emotions = emotions.map {
                    it.copy(
                        time = dateTimeConverters.convertToTime(it.created_at)
                    )
                }
            }
        }
        return emotionDataByMonthList
    }

}