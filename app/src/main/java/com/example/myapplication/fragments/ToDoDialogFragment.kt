package com.example.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.myapplication.databinding.FragmentToDoDialogBinding
import com.example.myapplication.users.TaskType
import com.example.myapplication.users.TasksRepository
import com.example.myapplication.users.UserTask
import com.google.firebase.Timestamp
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


class ToDoDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentToDoDialogBinding

    private val tasksRepository = TasksRepository()

    companion object { //tworzenie nowego obiektu
        const val TAG = "DialogFragment"
        @JvmStatic
        fun newInstance(taskId: String, task: String) =
            ToDoDialogFragment().apply {
                arguments = Bundle().apply {
                    putString("taskId", taskId)
                    putString("task", task)
                }
            }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? { // Inflate the layout for this fragment

        binding = FragmentToDoDialogBinding.inflate(inflater , container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.todoClose.setOnClickListener { //zamykanie
            dismiss()
        }

        binding.todoNextBtn.setOnClickListener { //następne zadanie
            val defZone = ZoneId.systemDefault()
            val localDate = LocalDate.parse(binding.todoStart.text, DateTimeFormatter.ISO_DATE)

            val task = UserTask(
                type = TaskType.valueOf(binding.typeSpinner.selectedItem.toString()),
                user = binding.userSpinner.selectedItem.toString(),
                startDate = Timestamp(Date.from(localDate.atStartOfDay(defZone).toInstant()))
            )

            tasksRepository.save(task)
        }
    }
}