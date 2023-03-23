package com.bbx.myjournal

import app.cash.turbine.test
import com.bbx.myjournal.data.Emotion
import com.bbx.myjournal.data.EmotionDataByDayModel
import com.bbx.myjournal.data.EmotionDataByMonthModel
import com.bbx.myjournal.emotions.EmotionsUiState
import com.bbx.myjournal.emotions.MainViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*

@RunWith(JUnit4::class)
class MainViewModelTest {
    @get:Rule
    var mainCoroutineRule = MainDispatcherRule()

    private lateinit var viewModel: MainViewModel
    private lateinit var fakeRepo: FakeEmotionsRepository

    @Before
    fun setup(){
        fakeRepo = FakeEmotionsRepository()
        viewModel = MainViewModel(fakeRepo)
    }

    @Test
    fun getMainUiState() = runTest{
        val emotionUiState = EmotionsUiState()
        viewModel.mainUiState.test {
            assertEquals(emotionUiState,awaitItem())
        }
    }

    @Test
    fun getEmotionsGroupedByMonthDay()= runTest {
        val emotionAfter = Emotion(
            emotionId = 2,
            emotion_type = 1,
            emotion_notes = "testing",
            created_at = Date(1558764780000),
            time = "11:13"
        )

        val emotionDataByDayMerged  = EmotionDataByDayModel(
            day = 23,
            count = 2,
            emotions = listOf(emotionAfter),
            date = "Thu, 23"
        )

        val emotionGrouped = EmotionDataByMonthModel(
            month = "May",
            count = 1,
            sum = 2,
            days = listOf(emotionDataByDayMerged)
        )
        viewModel.getEmotionsGroupedByMonthDay()
        viewModel.mainUiState.test {
            assertEquals(EmotionsUiState(listOf(emotionGrouped)),awaitItem())
        }
    }
}