package com.bbx.myjournal.utils

import androidx.room.TypeConverter
import com.bbx.myjournal.Constants
import java.lang.Exception
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class Converters {

    var df: DateFormat = SimpleDateFormat(Constants.TIME_STAMP_FORMAT,Locale.getDefault())

    @TypeConverter
    fun fromTimestamp(value: String?): Date? {
        return if (value != null) {
            try {
                return df.parse(value)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            null
        } else {
            null
        }
    }

    @TypeConverter
    fun dateToTimestamp(value: Date?): String? {
        return if (value == null) null else df.format(value)
    }

}