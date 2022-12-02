package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.example.myapplication.databinding.FragmentMenuCaregiverBinding
import kotlinx.android.synthetic.main.fragment_menu_caregiver.*
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R

class MenuCaregiverFragment : Fragment() {

    private lateinit var binding: FragmentMenuCaregiverBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu_caregiver, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addToDoList.setOnClickListener {
            findNavController().navigate(R.id.action_menuCaregiverFragment_to_homeFragment)
        }

    }



}