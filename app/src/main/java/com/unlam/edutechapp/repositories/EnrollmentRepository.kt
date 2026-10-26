package com.unlam.edutechapp.repositories

import com.unlam.edutechapp.data.Enrollment

object EnrollmentRepository {

    private val enrollments = mutableListOf<Enrollment>()

    init {
        enrollments.add(Enrollment(1L, 1510L, mutableListOf(1L, 3L, 5L)))
        enrollments.add(Enrollment(2L, 1504L, mutableListOf(2L, 4L, 6L)))
        enrollments.add(Enrollment(3L, 2802L, mutableListOf(1L, 5L, 7L)))
    }

    fun get(): List<Enrollment> {
        return enrollments
    }

    fun add(enrollment: Enrollment) {
        enrollments.add(enrollment)
    }

    fun getByUserId(userId: Long): Enrollment? {
        return enrollments.find { enrollment -> enrollment.userId == userId }

    }

    fun addCourseIds(enrollmentId: Long, courseIds: List<Long>) {
        val enrollment = enrollments.find { enrollment -> enrollment.id == enrollmentId }
        if (enrollment != null) {
            enrollment.coursesIds.addAll(courseIds)
        }
    }

    fun getNextId(): Long {
        return enrollments.size.toLong() + 1
    }
}