package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentWellBeingBinding
import kotlinx.android.synthetic.main.fragment_menu_caregiver.*
import kotlinx.android.synthetic.main.fragment_well_being.*


class WellBeingFragment : Fragment() {
    private lateinit var binding: FragmentWellBeingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_well_being, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        good.setOnClickListener {
            findNavController().navigate(R.id.action_wellBeingFragment_to_menuPatientFragment)
        }

        soNoBad.setOnClickListener {
            findNavController().navigate(R.id.action_wellBeingFragment_to_menuPatientFragment)
        }

        tired.setOnClickListener {
            findNavController().navigate(R.id.action_wellBeingFragment_to_menuPatientFragment)

        }
        bad.setOnClickListener {
            findNavController().navigate(R.id.action_wellBeingFragment_to_menuPatientFragment)

        }


    }

}