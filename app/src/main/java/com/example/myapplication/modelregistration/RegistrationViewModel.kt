package com.example.myapplication.modelregistration

import androidx.lifecycle.ViewModel
import com.example.myapplication.users.User
import com.example.myapplication.users.UserRepository

class RegistrationViewModel : ViewModel() {
    private val repository = UserRepository()

    fun createNewMainUser(user: User){
        repository.save(user)
    }
}