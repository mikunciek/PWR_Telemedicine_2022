package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.users.User
import com.example.myapplication.users.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.time.LocalDate
import java.util.*

class MainActivity : Activity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = Firebase.auth
//        val u = User();
//        u.uid = UUID.randomUUID().toString();
//        u.email = "test@test.pl"
//        u.birthDate = LocalDate.now().toString()
//        u.firstName = "Tomasz"
//        u.isDoctor = true
//        u.lastName = "Chada"
//
//        val repo = UserRepository()
//        repo.save(u)
    }

    override fun onStart() {
        super.onStart()
        val currentUSer: FirebaseUser? = auth.currentUser

        if (currentUSer == null) {
            this.startActivity(Intent(this, LoginActivity::class.java))
        }

        // wylogowanie
//        auth.signOut()
//        UserRepository.auth.signOut();
    }
}