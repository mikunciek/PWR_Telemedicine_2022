package com.example.myapplication.users

import android.util.Log
import com.example.myapplication.Repository
import com.google.firebase.auth.FirebaseAuth

class UserRepository: Repository() {

    private final val COLLECTION = "users"

    companion object FirebaseManagerAuth{
        val auth = FirebaseAuth.getInstance()
        fun getCurrentUserID(): String? = auth.currentUser?.uid
    }


    fun save(user: User) {
        cloud.collection(COLLECTION)
            .document(user.uid!!)
            .set(user);

        Log.d(FIREBASE_DEBUG, user.uid)
    }
}