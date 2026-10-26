package com.unlam.edutechapp.data

data class Course(
    val id: Long,
    val name: String,
    val date: String,
    val time: String,
    val location: String,
    val instructor: String,
    val image: String,
    val capacity: Int
)
