package com.example.myapplication.fragments
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R

import kotlinx.android.synthetic.main.fragment_finger_tapping.*

class FingerTapping : Fragment() {

    var count = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_finger_tapping, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // time count down for 30 seconds,
        // with 1 second as countDown interval
        object : CountDownTimer(15000, 1000) {

            // Callback function, fired on regular interval
            override fun onTick(millisUntilFinished: Long) {
                timerCounter.text = (millisUntilFinished / 1000).toString()
            }

            // Callback function, fired
            // when the time is up
            override fun onFinish() {

                saveScore()
                clickButton.visibility = View.GONE

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
        val score =numberOfClick
        //zapis do bazy
    }


}