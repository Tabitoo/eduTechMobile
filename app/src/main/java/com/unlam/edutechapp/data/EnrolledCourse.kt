package com.unlam.edutechapp.data

data class EnrolledCourse(
    val id: Long,
    val courseId: Long,
    val duration: Int,
    val type: String,
    var completed: Boolean = false
)
