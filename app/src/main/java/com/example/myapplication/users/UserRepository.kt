package com.example.myapplication.users

import android.util.Log
import com.example.myapplication.Repository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.tasks.await

class UserRepository: Repository() {

    private final val COLLECTION = "users"

    companion object FirebaseManagerAuth{
        val auth = FirebaseAuth.getInstance()
        fun getCurrentUserID(): String? = auth.currentUser?.uid
    }


    fun save(user: User) {
        cloud.collection(COLLECTION)
            .document(user.uid)
            .set(user);

        Log.d(FIREBASE_DEBUG, user.uid)
    }

    fun getUser(uid: String): Task<DocumentSnapshot>{

        return cloud.collection(COLLECTION)
            .document(uid).get()

    }

    fun createFromFirebaseUser(firebaseUser: FirebaseUser) {
        val user = User(
            uid = firebaseUser.uid,
            email = firebaseUser.email ?: "",
            firstName = firebaseUser.displayName ?: "",
            phone = firebaseUser.phoneNumber,
        )

        this.save(user)
    }
}