package com.example.myapplication.users

import android.util.Log
import com.example.myapplication.Repository
import com.google.android.gms.tasks.Task
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

class TasksRepository: Repository() {
    private final val COLLECTION = "tasks"
    private val userRepository = UserRepository()

    companion object FirebaseManagerAuth {
        val auth = FirebaseAuth.getInstance()
        fun getCurrentUserID(): String? = auth.currentUser?.uid
        private const val TASK_REPOSITORY = "TASK_REPOSTITORY_DEBBUG"
    }

    fun save(task: UserTask) {
        cloud.collection(COLLECTION)
            .document(task.uid)
            .set(task);

        Log.d(FIREBASE_DEBUG, task.uid)
    }

    fun getTask(uid: String): Task<DocumentSnapshot> {
        return cloud.collection(COLLECTION).document(uid).get()
    }

    fun getTasksByUser(user: User): Task<QuerySnapshot> {
        return cloud.collection(COLLECTION).whereEqualTo("user", user.uid).get();
    }

    fun getTasksByPatients(unit: (List<UserTask>) -> Unit) {
        userRepository.getCurrentUserPatients { users ->

                cloud.collection(COLLECTION).get()
                    .addOnSuccessListener {
                        Log.d(TasksRepository.TASK_REPOSITORY, "Lista wyświetlona!")

                        if (!it.isEmpty) {
                            val tasksList = it.toObjects(UserTask::class.java)

                            val tt = tasksList.filter { fil ->
                                users.map { us -> us.uid }.contains(fil.user)
                            }

                            unit.invoke(tt)
                        }


                    }
            }

    }

    fun addSnapshotListenerForPatients(unit: (list: List<UserTask>) -> Unit) {
        cloud.collection(COLLECTION).addSnapshotListener{snapshot, e ->
            userRepository.getCurrentUserPatients { users ->
                val tasksList = snapshot!!.toObjects(UserTask::class.java)

                val tt = tasksList.filter { fil ->
                    users.map { us -> us.uid }.contains(fil.user)
                }

                unit.invoke(tt)
            }
        }
    }

    fun closeTaskWithResults(uid: String, result: String) {
        db.collection(COLLECTION)
            .document(uid)
            .set({
                "result" to result
                "closeDate" to Timestamp(
                    Date.from(
                        LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()
                    )
                )
                "status" to TaskStatus.DONE
            })
    }

}





