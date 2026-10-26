package com.unlam.edutechapp.services

import com.unlam.edutechapp.data.Course
import com.unlam.edutechapp.data.CourseDetail
import com.unlam.edutechapp.data.EnrolledCourse
import com.unlam.edutechapp.data.Enrollment
import com.unlam.edutechapp.data.PurchasedCourses
import com.unlam.edutechapp.data.User
import com.unlam.edutechapp.enums.PaymentsEnum
import com.unlam.edutechapp.exceptions.CourseFullException
import com.unlam.edutechapp.exceptions.InsufficientFoundsException
import com.unlam.edutechapp.exceptions.NotLogInException
import com.unlam.edutechapp.payments.MasterCard
import com.unlam.edutechapp.payments.MercadoPago
import com.unlam.edutechapp.payments.PaymentMethod
import com.unlam.edutechapp.payments.Visa
import com.unlam.edutechapp.repositories.CourseRepository
import com.unlam.edutechapp.repositories.EnrolledCourseRepository
import com.unlam.edutechapp.repositories.EnrollmentRepository
import com.unlam.edutechapp.repositories.UserRepository

object EduTechService {

    private var user: User? = null

    val isLoggedIn: Boolean
        get() = user != null

    private fun checkCourseMaxCapacity(course: Course): Boolean {

        val courseId = course.id
        var currentStudents: Int = 0

        val allEnrolledCoursesId = EnrollmentRepository.get().flatMap { enrollment -> enrollment.coursesIds }

        val foundEnrolledCourses = EnrolledCourseRepository.getByCourseIds(allEnrolledCoursesId.toMutableList())

        foundEnrolledCourses.forEach { enrolledCourse -> if(enrolledCourse.courseId == courseId) currentStudents += 1 }

        return currentStudents >= course.capacity

    }

    private fun generateCourseDetail(courses: List<Course>, enrolledCourses: List<EnrolledCourse>): List<CourseDetail> {
        val result = mutableListOf<CourseDetail>()

        for (course in courses) {
            for (enrolled in enrolledCourses) {
                if (course.id == enrolled.courseId) {
                    result.add(CourseDetail(course, enrolled))
                }
            }
        }

        return result
    }

    fun login(nickName: String, password: String): Boolean {
        user =  UserRepository.login(nickName, password)

        return user != null
    }

    fun getMyCourses(): List<CourseDetail> {

        val foundEnrollment: Enrollment? = EnrollmentRepository.get().find { enrollment -> enrollment.userId == user?.id}

        if(foundEnrollment != null) {

            val assignedEnrolledCourses: List<EnrolledCourse> = EnrolledCourseRepository.getByCourseIds(foundEnrollment.coursesIds)

            val assignedCourses: List<Course> = CourseRepository.get().filter { course -> assignedEnrolledCourses.map { it.courseId }.contains(course.id) }

            val courseDetailsList = generateCourseDetail(assignedCourses, assignedEnrolledCourses)

            return courseDetailsList

        }
        return emptyList()

    }

    fun  getCompletedCourses(): List<CourseDetail> {

        val foundEnrollment: Enrollment? = EnrollmentRepository.get().find {enrollment -> enrollment.userId == user?.id}

        if(foundEnrollment != null) {

            val assignedEnrolledCourses: List<EnrolledCourse> = EnrolledCourseRepository.getByCourseIds(foundEnrollment.coursesIds).filter { enrolledCourse -> enrolledCourse.completed }

            val assignedCourses: List<Course> = CourseRepository.get().filter { course -> assignedEnrolledCourses.map { it.courseId }.contains(course.id) }

            val courseDetailsList = generateCourseDetail(assignedCourses, assignedEnrolledCourses)

            return courseDetailsList

        }

        return emptyList()
    }

    fun getAvailableCourses(): List<Course> {

        val foundEnrollment: Enrollment? = EnrollmentRepository.get().find {enrollment -> enrollment.userId == user?.id}

        if(foundEnrollment != null) {

            val assignedEnrolledCourses: List<EnrolledCourse> = EnrolledCourseRepository.getByCourseIds(foundEnrollment.coursesIds)

            val unassignedCourses: List<Course> = CourseRepository.get().filterNot { course -> assignedEnrolledCourses.map { it.courseId }.contains(course.id) }

            return unassignedCourses

        }

        val totalCourses = CourseRepository.get()

        return totalCourses
    }

    fun purchaseCourse(courses: Set<Long>, paymentMethod: PaymentsEnum): PurchasedCourses {

        if(!isLoggedIn) throw NotLogInException()

        val foundCourses = CourseRepository.getByCourseIds(courses)
        val totalCourses: MutableList<Course> = mutableListOf()

        for (course in foundCourses) {
            if(!checkCourseMaxCapacity(course)) {
                totalCourses.add(course)

            }
        }

        if(totalCourses.isEmpty()) throw CourseFullException()

        val totalPriceCourses: Double = totalCourses.size * 10000.0

        val payment: PaymentMethod = when (paymentMethod) {
            PaymentsEnum.MERCADOPAGO -> MercadoPago()
            PaymentsEnum.VISA -> Visa()
            else -> MasterCard()
        }

        val totalAmount = payment.createPayment(totalPriceCourses);

        if(user!!.money < totalAmount) throw InsufficientFoundsException()

        user?.money = user!!.money - totalAmount

        val foundEnrollment = EnrollmentRepository.getByUserId(user?.id!!)

        val enrolledCoursesIds = mutableListOf<Long>()

        totalCourses.forEach { course ->

            val enrolledCourseId = EnrolledCourseRepository.getNextId()

            val newEnrolledCourse = EnrolledCourse(enrolledCourseId, course.id, 49, "virtual")

            enrolledCoursesIds.add(newEnrolledCourse.id)
            EnrolledCourseRepository.add(newEnrolledCourse)

        }

        if(foundEnrollment != null) {

            EnrollmentRepository.addCourseIds(foundEnrollment.id, enrolledCoursesIds)

        } else {
            val enrollmentId = EnrollmentRepository.getNextId()

            val newEnrollment: Enrollment = Enrollment(enrollmentId, user!!.id, enrolledCoursesIds)

            EnrollmentRepository.add(newEnrollment)
        }

        val finalResult = PurchasedCourses(totalCourses.map { course -> course.name }, totalAmount)

        return finalResult
    }
}