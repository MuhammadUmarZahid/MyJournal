package com.bbx.myjournal

import app.cash.turbine.test
import io.mockk.*
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual.equalTo
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*


@RunWith(JUnit4::class)
class DefaultEmotionsRepositoryTest {

    //Unit under test
    private lateinit var emotionsRepository: DefaultEmotionsRepository

    //mocked dependency

    private lateinit var emotionDao:EmotionDao
    private lateinit var dateTimeConverters: DateTimeConverters


    @Before
    fun setUp() {
        emotionDao = mockk<EmotionDao>()
        dateTimeConverters = DateTimeConverters()
        emotionsRepository = DefaultEmotionsRepository(emotionDao,dateTimeConverters)
    }



    @Test
    fun createEmotion() = runTest{
        //when
        coEvery { emotionDao.insertEmotion(any()) } just Runs

        //expected
        emotionsRepository.createEmotion("I am feeling happy",1)

        coVerify { emotionDao.insertEmotion(any()) }
    }

    @Test
    fun getEmotions()= runTest {
        //given
        val emotionEntity = EmotionEntity(
            emotionId = 1,
            emotionNotes = "Feeling Sad",
            emotionType = -1,
            createdAt = Date(10000)
        )

        //when
        coEvery { emotionDao.getAllEmotions() } returns flowOf(listOf(emotionEntity))


        //expect
        emotionsRepository.getEmotions().test {
            assertThat(awaitItem(),equalTo(listOf(emotionEntity.toDomain())))
            cancelAndConsumeRemainingEvents()

            coVerify { emotionDao.getAllEmotions() }
        }

    }

    @Test
    fun getEmotionsGroupedByMonthDay() = runTest(){

        val emotion = Emotion(
            emotionId = 2,
            emotion_type = 1,
            emotion_notes = "testing",
            created_at = Date(1558764780000)
        )

        val emotionDataByDayEntity = EmotionDataByDayEntity(
            day = 25,
            count = 2
        )

        val emotionDataByMonthEntity = EmotionDataByMonthEntity(
            month = "2019-05",
            count = 2,
            sum = 20
        )

        val emotionAfter = Emotion(
            emotionId = 2,
            emotion_type = 1,
            emotion_notes = "testing",
            created_at = Date(1558764780000),
            time = "11:13"
        )

        val emotionDataByDayMerged  = EmotionDataByDayModel(
            day = 25,
            count = 2,
            emotions = listOf(emotionAfter),
            date = "Sat, 25"
        )

        val emotionGrouped = EmotionDataByMonthModel(
            month = "May",
            count = 2,
            sum = 20,
            days = listOf(emotionDataByDayMerged)
        )

        //when
        coEvery  { emotionDao.getEmotionDataByMonth() } returns listOf(emotionDataByMonthEntity)
        coEvery { emotionDao.getEmotionDataByDay("2019-05") } returns listOf(emotionDataByDayEntity)
        coEvery { emotionDao.getEmotionsForDate("2019-05-25") } returns listOf(emotion)

        //expected
        launch {
            emotionsRepository.getEmotionsGroupedByMonthDay().test {
                assertThat(awaitItem(), equalTo(listOf(emotionGrouped)))
                cancelAndConsumeRemainingEvents()

                coVerify { emotionDao.getEmotionDataByMonth() }
                coVerify { emotionDao.getEmotionDataByDay("2019-05") }
                coVerify { emotionDao.getEmotionsForDate("2019-05-25") }

            }
        }



    }

}