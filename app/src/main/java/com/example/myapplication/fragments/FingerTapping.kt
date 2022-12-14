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
    var duration = 15  //set end time in seconds


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val hour = hourTimer
        val minute = minuteTimer
        val second = secondTimer
        val click = clickButton

        binding = FragmentFingerTappingBinding.inflate(inflater , container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        clickButton.setOnClickListener{
            onTap()

        }
    }


    private fun onTap(){
        count++
        numberOfClick.text =count.toString()
    }

}