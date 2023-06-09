package com.bbx.myjournal

import app.cash.turbine.test
import com.bbx.myjournal.createemotion.CreateEmotionViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class CreateEmotionViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainDispatcherRule()

    private lateinit var viewModel: CreateEmotionViewModel

    @Before
    fun setup(){
        viewModel = CreateEmotionViewModel(FakeEmotionsRepository())
    }

    @Test
    fun getCreatedState()= runTest {
        viewModel.createdState.test {
            assertEquals(false,awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun onCreateEmotion() = runTest{
        viewModel.onCreateEmotion("testing ",1)
        viewModel.createdState.test {
            assertEquals(true,awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }
}