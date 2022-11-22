package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import kotlinx.android.synthetic.main.activity_caregiverpanel.*


class CaregiverPanel : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_caregiverpanel)


        patients.setOnClickListener {
            this.startActivity(Intent(this, Patients::class.java))
        }

        addPatient.setOnClickListener{

        }

        addToDoList.setOnClickListener {

        }

        checkDoneList.setOnClickListener {

        }


        library.setOnClickListener {
            this.startActivity(Intent(this, GuideCaregiver::class.java))
        }



    }
}