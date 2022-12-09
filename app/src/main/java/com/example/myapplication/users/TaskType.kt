package com.example.myapplication.users

import com.example.myapplication.R

enum class TaskType(val navigationId: Int)  {
    FINGER_TAP(R.id.action_menuPatientFragment_to_fingerTapping),
    MEMORY_QUIZ(R.id.action_menuPatientFragment_to_memoryQuiz)
}