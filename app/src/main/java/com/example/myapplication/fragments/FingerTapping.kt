package com.example.myapplication.fragments

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.databinding.FragmentFingerTappingBinding

import kotlinx.android.synthetic.main.fragment_finger_tapping.*


class FingerTapping : Fragment() {

    private lateinit var binding: FragmentFingerTappingBinding
    private var timer: CountDownTimer? = null
    var count = 0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = FragmentFingerTappingBinding.inflate(inflater , container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        object : CountDownTimer(15, 1){
            override fun onTick(remaning: Long) {
                timerCounter.text = remaning.toString()
            }

            override fun onFinish() {
                timerView.text = "Test wykonano"
            }
        }

        onTap.setOnClickListener{
            count++

            numberOfClick.text =count.toString()
        }
    }

    override fun onStart(){
        super.onStart()
        timer?.start()
    }

    override fun onStop(){
        super.onStop()
        timer?.cancel()
    }
}