package com.unlam.edutechapp.repositories

import com.unlam.edutechapp.data.EnrolledCourse

object EnrolledCourseRepository {

    private val enrolledCourses = mutableListOf<EnrolledCourse>()

    init {
        enrolledCourses.add(EnrolledCourse(1L, 4L, 60, "Presencial"))
        enrolledCourses.add(EnrolledCourse(2L, 3L, 45, "Virtual", true))
        enrolledCourses.add(EnrolledCourse(3L, 1L, 60, "Presencial"))
        enrolledCourses.add(EnrolledCourse(4L, 5L, 90, "Presencial", true))
        enrolledCourses.add(EnrolledCourse(5L, 2L, 60, "Virtual"))
        enrolledCourses.add(EnrolledCourse(6L, 6L, 45, "Presencial",true))
        enrolledCourses.add(EnrolledCourse(7L, 7L, 60, "Virtual"))
    }

    fun get(): MutableList<EnrolledCourse> {
        return enrolledCourses
    }

    fun getByCourseIds(courseIds: MutableList<Long>): MutableList<EnrolledCourse> {
        return enrolledCourses.filter { courseIds.contains(it.id) }.toMutableList()
    }

    fun getNextId(): Long {
        return enrolledCourses.size.toLong() + 1
    }

    fun add(course: EnrolledCourse) {
        enrolledCourses.add(course)
    }

}