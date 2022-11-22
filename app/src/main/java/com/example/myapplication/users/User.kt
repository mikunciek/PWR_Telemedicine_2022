package com.example.myapplication.users

data class User(
    var uid: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var phone: Number? = null,
    var caregiver: String = ""
)
