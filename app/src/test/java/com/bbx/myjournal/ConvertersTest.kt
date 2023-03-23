package com.bbx.myjournal

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*

@RunWith(JUnit4::class)
class ConvertersTest {

    private lateinit var converters: Converters


    @Before
    fun setup(){
        converters = Converters()
    }


    @Test
    fun getDf() {
    }

    @Test
    fun setDf() {
    }

    @Test
    fun fromTimestamp() = run {
        val inputDateString = "1990-07-28 16:44"
        val outputDate = Date(649165440000)

        val date = converters.fromTimestamp(inputDateString)
        assertThat(date, IsEqual.equalTo(outputDate))
    }

    @Test
    fun dateToTimestamp() = runTest {
        val outputDateString = "1990-07-28 16:44"
        val inputDate = Date(649165440000)

        val date = converters.dateToTimestamp(inputDate)
        assertThat(date, IsEqual.equalTo(outputDateString))
    }
}