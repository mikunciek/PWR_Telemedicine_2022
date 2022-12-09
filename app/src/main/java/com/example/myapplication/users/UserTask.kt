package com.example.myapplication.users

import com.google.firebase.Timestamp
import com.google.type.DateTime
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.util.Date
import java.util.UUID

data class UserTask(
    var uid: String = UUID.randomUUID().toString(),
    var user: String = "",
    var type: TaskType? = null,
    var status: TaskStatus = TaskStatus.TODO,
    var endDate: Timestamp = Timestamp(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
)