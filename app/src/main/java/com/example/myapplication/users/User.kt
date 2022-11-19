package com.example.myapplication.users

import java.time.LocalDate

data class User(
    var uid: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var birthDate: String = "",
    var isDoctor: Boolean = false
)
