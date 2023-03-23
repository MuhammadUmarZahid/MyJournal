package com.bbx.myjournal

import com.bbx.myjournal.utils.DateTimeConverters
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*

@RunWith(JUnit4::class)
class DateTimeConvertersTest {

    private lateinit var dateTimeConverters: DateTimeConverters

    @Before
    fun setup(){
        dateTimeConverters = DateTimeConverters()
    }

    @Test
    fun convertToTime() = runTest{
        val inputDate = Date(1679570018786)//16:13 in local time zone
        val output = dateTimeConverters.convertToTime(inputDate)
        assertEquals("16:13",output)
    }

    @Test
    fun getMonthName() = runTest{
        val input = "2023-03" //march
        val output = dateTimeConverters.getMonthName(input)
        assertEquals("March",output)
    }

    @Test
    fun getDayFormat()= runTest{
        val input = "2023-03-23" //Thu, 23
        val output = dateTimeConverters.getDayFormat(input)
        assertEquals("Thu, 23",output)
    }
}