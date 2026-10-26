package com.unlam.edutechapp.data

data class User(
    val id: Long,
    val nickname: String,
    val password: String,
    val name: String,
    val surname: String,
    var money: Double,
    val createdDate: String
) {
    val fullName: String
        get() = "$nickname $surname"
}

