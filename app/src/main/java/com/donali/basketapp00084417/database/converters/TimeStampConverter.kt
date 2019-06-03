package com.donali.basketapp00084417.database.converters

import android.util.Log
import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*

class TimeStampConverter {


    companion object {
        val dateFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy")

        @TypeConverter
        @JvmStatic
        fun fromTimeStamp(value: String): Date {
            return   dateFormat.parse(value)
        }
        @TypeConverter
        @JvmStatic
        fun toTimeStamp(value: Date): String =
            value.toString()

    }
}