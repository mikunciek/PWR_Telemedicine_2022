package com.example.myapplication.users

import android.util.Log
import com.example.myapplication.Repository
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

class TasksRepository: Repository() {
    private final val COLLECTION = "tasks"

    fun save(task: UserTask) {
        cloud.collection(COLLECTION)
            .document(task.uid)
            .set(task);

        Log.d(FIREBASE_DEBUG, task.uid)
    }

    fun getTask(uid: String): Task<DocumentSnapshot> {
        return cloud.collection(COLLECTION).document(uid).get();
    }

    fun getTasksByUser(user: User): Task<QuerySnapshot> {
        return cloud.collection(COLLECTION).whereEqualTo("user", user.uid).get();
    }
}