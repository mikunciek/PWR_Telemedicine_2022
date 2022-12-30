package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentWellBeingBinding
import com.example.myapplication.users.TaskStatus
import com.example.myapplication.users.TasksRepository
import com.example.myapplication.users.UserRepository
import com.example.myapplication.users.UserTask
import com.google.firebase.Timestamp
import kotlinx.android.synthetic.main.fragment_finger_tapping.*
import kotlinx.android.synthetic.main.fragment_menu_caregiver.*
import kotlinx.android.synthetic.main.fragment_well_being.*
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*


class WellBeingFragment : Fragment() {
    private lateinit var binding: FragmentWellBeingBinding
    private val tasksRepository = TasksRepository()
    private val userRepository = UserRepository()
    private lateinit var task: UserTask

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
            saveScore("Dobrze")
            findNavController().navigate(R.id.action_wellBeingFragment_to_menuPatientFragment)

        }

        soNoBad.setOnClickListener {
            saveScore("Średnio")
            findNavController().navigate(R.id.action_wellBeingFragment_to_menuPatientFragment)
        }

        tired.setOnClickListener {
            saveScore("Zmęczony")
            findNavController().navigate(R.id.action_wellBeingFragment_to_menuPatientFragment)

        }
        bad.setOnClickListener {
            saveScore("Źle")
            findNavController().navigate(R.id.action_wellBeingFragment_to_menuPatientFragment)

        }

    }


    private fun saveScore(result:String) {

        task.result = result
        task.status = TaskStatus.DONE
        task.closeDate = Timestamp(
            Date.from(
                LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()
            )
        )
        tasksRepository.save(task)
    }

}