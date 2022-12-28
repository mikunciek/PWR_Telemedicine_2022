package com.example.myapplication.fragments

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.DialogFragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentToDoDialogBinding
import com.example.myapplication.users.*
import com.google.firebase.Timestamp
import kotlinx.android.synthetic.main.fragment_to_do_dialog.*
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


class ToDoDialogFragment() : DialogFragment() {
    private lateinit var binding: FragmentToDoDialogBinding
    private val tasksRepository = TasksRepository()
    private val userRepository = UserRepository()

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

        initValues()

        binding.todoClose.setOnClickListener { //zamykanie
            dismiss()
        }

        binding.todoNextBtn.setOnClickListener { //następne zadanie
            val defZone = ZoneId.systemDefault()
            val localDate = LocalDate.parse(binding.todoStart.text, DateTimeFormatter.ISO_DATE)

            val task = UserTask(
                type = TaskType.findByTitle(binding.typeSpinner.selectedItem.toString()),
                user = (binding.userSpinner.selectedItem as User).uid,
                startDate = Timestamp(Date.from(localDate.atStartOfDay(defZone).toInstant())),
                description = binding.todoDescritption.toString()
            )
//TODO: ERROR - kiedy jest puste jakieś pole
            tasksRepository.save(task)

            dismiss()
        }
    }

    private fun initValues() {
        userRepository.getCurrentUserMustExist {
            view?.findViewById<Spinner>(R.id.type_spinner)?.adapter = ArrayAdapter(requireContext(),
                android.R.layout.simple_spinner_item, TaskType.values().map { i -> i.title})

            userRepository.getPatients(it.uid) { users ->
                view?.findViewById<Spinner>(R.id.user_spinner)
                    ?.adapter =
                    ArrayAdapter<User>(requireContext(),
                    android.R.layout.simple_spinner_dropdown_item, users)
            }
        }

    }

    override fun onResume() {
        val dialogWindow: Window? = dialog!!.window
        val lp: WindowManager.LayoutParams = dialogWindow!!.attributes
        lp.x = 50 // set your X position here
        lp.y = 500 // set your Y position here

        //dialogWindow.attributes = lp
        val params = dialogWindow.attributes
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        dialogWindow.setGravity(Gravity.END)



        super.onResume()
    }


    //TODO: format daty i godziny

}
