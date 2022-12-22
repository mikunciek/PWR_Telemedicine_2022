package com.example.myapplication.users

import com.example.myapplication.R

enum class TaskType(val navigationId: Int?, val icon: Int, val title: String)  {
    FINGER_TAP(R.id.action_menuPatientFragment_to_fingerTapping, R.drawable.ic_fingertaping, "Stukanie palcami"),
    MEMORY_QUIZ(R.id.action_menuPatientFragment_to_memoryQuiz, R.drawable.ic_quiz, "Testy pamięci"),
    MEDICINE(null, R.drawable.ic_medication, "Przypomnienie o lekach"),
    ACTIVITY(null, R.drawable.ic_activity_run, "Aktywność fizyczna"),
    CLICK(null, R.drawable.ic_happy, "Kliknij");

    companion object {
        fun findByTitle(title: String): TaskType {
            return values().first { it.title === title }
        }
    }

}