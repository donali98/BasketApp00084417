package com.donali.basketapp00084417.database.converters

import android.util.Log
import androidx.room.TypeConverter
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class TimeStampConverter {


    companion object {
        val dateFormat = SimpleDateFormat("YYYY-MM-DD HH:MM:SS.SSS", Locale.US)

        @TypeConverter
        @JvmStatic
        fun fromTimeStamp(value: String): Date =
            dateFormat.parse(value)

        @TypeConverter
        @JvmStatic
        fun toTimeStamp(value: Date): String =
            value.toString()

    }
}