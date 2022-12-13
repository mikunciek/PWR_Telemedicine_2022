package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.findNavController
import com.example.myapplication.users.User
import com.example.myapplication.users.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.*
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private var userRepository: UserRepository = UserRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = Firebase.auth

    }

    override fun onStart() {
        super.onStart()
        val currentUser: FirebaseUser? = auth.currentUser
        if (currentUser === null) {
            this.startActivity(Intent(this, LoginActivity::class.java))
        }

        userRepository.getCurrentUser()?.addOnSuccessListener {
            val user = it.toObject(User::class.java);

            if (user === null) {
               return@addOnSuccessListener
            }

            if (user!!.caregiver.isEmpty()) {
                this.nav_host_fragment.findNavController()
                    .navigate(R.id.action_blankMenuFragment_to_menuCaregiverFragment)
            } else {
                this.nav_host_fragment.findNavController()
                   .navigate(R.id.action_blankMenuFragment_to_menuPatientFragment)
            }
       }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

   override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                this.startActivity(Intent(this, SettingsActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}