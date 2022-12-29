package com.example.myapplication.users

import com.example.myapplication.Repository

class LocationRepository : Repository() {

    private val COLLECTION = "localization"

    fun save(location: Localization) {
        cloud.collection(COLLECTION)
            .document(location.user).set(location)
    }

    fun getLocation(user: User, unit: (Localization) -> Unit) {
        cloud.collection(COLLECTION).document(user.uid).get()
            .addOnSuccessListener {
                if(it.exists()) {
                    unit.invoke(it.toObject(Localization::class.java)!!)
                }
            }
    }
}