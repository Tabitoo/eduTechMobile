package com.unlam.edutechapp.exceptions

import com.unlam.edutechapp.data.Course

class CourseAlreadyPurchasedException(val courses: List<Course>): Exception("Courses ${courses.joinToString { it.name }} already Purchased")

