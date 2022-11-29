package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.myapplication.fragments.HomeFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.*
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_caregiverpanel.*


class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = Firebase.auth


        patients.setOnClickListener {
            this.startActivity(Intent(this, HomeFragment::class.java))
        }

        addPatient.setOnClickListener{

        }

        addToDoList.setOnClickListener {
            this.startActivity(Intent(this, Patients::class.java))
        }

        checkDoneList.setOnClickListener {
        }


        library.setOnClickListener {
            this.startActivity(Intent(this, GuideCaregiver::class.java))
        }

        settings.setOnClickListener {
            this.startActivity(Intent(this, SettingsActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser: FirebaseUser? = auth.currentUser
        if (currentUser == null) {
            this.startActivity(Intent(this, LoginActivity::class.java))
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



    override fun checkCallingUriPermissions(uris: MutableList<Uri>, modeFlags: Int): IntArray {

        fun call(view: View) {
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + "112")
            startActivity(dialIntent)
        }
        return super.checkCallingUriPermissions(uris, modeFlags)
    }


}