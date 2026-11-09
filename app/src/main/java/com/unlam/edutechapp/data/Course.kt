package com.unlam.edutechapp.data
import android.os.Build
import androidx.annotation.RequiresApi
import java.io.Serializable
import java.time.LocalDate
import java.time.temporal.ChronoUnit

data class Course(
    val id: Long,
    val name: String,
    val date: String,
    val endDate: String,
    val time: String,
    val location: String,
    val instructor: String,
    val image: String,
    val capacity: Int
): Serializable {
    val durationDays: Int
        @RequiresApi(Build.VERSION_CODES.O)
        get() {
            val start = LocalDate.parse(date)
            val end = LocalDate.parse(endDate)
            return ChronoUnit.DAYS.between(start, end).toInt()
        }
}
