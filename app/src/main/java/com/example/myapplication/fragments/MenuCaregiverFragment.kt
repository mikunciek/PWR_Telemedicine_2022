package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentMenuCaregiverBinding
import kotlinx.android.synthetic.main.fragment_menu_caregiver.*

import com.example.myapplication.R
import com.example.myapplication.users.UserRepository
import com.google.firebase.auth.ktx.userProfileChangeRequest

class MenuCaregiverFragment : Fragment() {

    private lateinit var binding: FragmentMenuCaregiverBinding
    private val userRepository = UserRepository()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu_caregiver, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        findPatient.setOnClickListener {
            findNavController().navigate(R.id.action_menuCaregiverFragment_to_locationPatientsFragment)
        }

        library.setOnClickListener {
           findNavController().navigate(R.id.action_menuCaregiverFragment_to_guideFragment)
        }

        settings.setOnClickListener{
            findNavController().navigate(R.id.action_menuCaregiverFragment_to_settingsActivity)
        }

        addPatient.setOnClickListener {
            findNavController().navigate(R.id.action_menuCaregiverFragment_to_newUserFragment)
        }

        mmseTest.setOnClickListener{
           findNavController().navigate(R.id.action_menuCaregiverFragment_to_MMSETestFragment)
        }

        statusTasks.setOnClickListener{
            findNavController().navigate(R.id.action_menuCaregiverFragment_to_patientsTasksStatusFragment)
        }

        userRepository.getCurrentUserMustExist {
            idCaregiver.text = String.format("Witaj: %s %s", it.firstName, it.lastName)
        }

    }
}