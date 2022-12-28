package com.example.myapplication.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentFingerTappingBinding
import com.example.myapplication.users.*
import com.google.firebase.Timestamp
import kotlinx.android.synthetic.main.fragment_finger_tapping.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class FingerTapping : Fragment() {

    private lateinit var  binding: FragmentFingerTappingBinding
    private val tasksRepository = TasksRepository()
    private val userRepository = UserRepository()
    var count = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_finger_tapping, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // time count down for 30 seconds,
        // with 1 second as countDown interval
        object : CountDownTimer(15000, 1000) {


            override fun onTick(millisUntilFinished: Long) {
                timerCounter.text = (millisUntilFinished / 1000).toString()
            }


            override fun onFinish() {


                clickButton.visibility = View.GONE
                saveScore()
                timerView.text = "Gotowe, kliknij by powrócić"

                timerView.setOnClickListener{

                    findNavController().navigate(R.id.action_fingerTapping_to_menuPatientFragment)
                }



            }
        }.start()


        clickButton.setOnClickListener{
            count++
            numberOfClick.text =count.toString()

        }

    }

    private fun saveScore() {
        //zapis do bazy

        userRepository.getCurrentUserMustExist {
            tasksRepository.closeTaskWithResults(it.uid, numberOfClick.toString())
        }
    }


}