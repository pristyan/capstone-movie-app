package com.aoc.core.util

import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Chandra.
 **/

fun String.parseDate(): String {
    return try {
        var date: Date?
        SimpleDateFormat("yyyy-MM-dd", Locale.US)
            .also { date = it.parse(this) }
            .apply { applyPattern("dd/MM/yyyy") }
            .run { if (date != null) format(date as Date) else "" }
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}