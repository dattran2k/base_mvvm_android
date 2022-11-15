package com.news.thanhnien.helper

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
object TimeUtils {


    fun convertTimeToString(time: Long): String {
        val df = SimpleDateFormat("dd/MM/yyyy")
        var timeConvert = time
        if (time.toString().length < 12) timeConvert = time * 1000
        return df.format((timeConvert))

    }
}