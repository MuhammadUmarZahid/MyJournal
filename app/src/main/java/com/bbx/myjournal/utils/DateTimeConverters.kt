package com.bbx.myjournal.utils

import com.bbx.myjournal.Constants
import java.lang.Exception
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DateTimeConverters @Inject constructor(){


    fun convertToTime(value: Date?): String? {
        var df: DateFormat = SimpleDateFormat(Constants.TIME_FORMAT,Locale.getDefault())
        return if (value == null) null else df.format(value)
    }

    fun getMonthName(value:String): String? {
        var df: DateFormat = SimpleDateFormat(Constants.MONTH_CONV_FORMAT,Locale.getDefault())
        return if (value != null) {
            try {
                val date = df.parse(value)
                var df: DateFormat = SimpleDateFormat(Constants.MONTH_FORMAT,Locale.getDefault())
                return if (value == null) null else df.format(date)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            null
        } else {
            null
        }
    }

    fun getDayFormat(value:String): String? {
        var df: DateFormat = SimpleDateFormat(Constants.DAY_CONV_FORMAT,Locale.getDefault())
        return if (value != null) {
            try {
                val date = df.parse(value)
                var df: DateFormat = SimpleDateFormat(Constants.DAY_FORMAT,Locale.getDefault())
                return if (value == null) null else df.format(date)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            null
        } else {
            null
        }
    }


}