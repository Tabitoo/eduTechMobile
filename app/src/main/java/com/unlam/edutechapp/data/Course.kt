package com.unlam.edutechapp.data
import java.io.Serializable

data class Course(
    val id: Long,
    val name: String,
    val date: String,
    val time: String,
    val location: String,
    val instructor: String,
    val image: String,
    val capacity: Int
): Serializable
