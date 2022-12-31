package com.example.myapplication.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.utils.adapter.MenuPatientListAdapter
import com.example.myapplication.R
import com.example.myapplication.users.TasksRepository
import com.example.myapplication.users.User
import com.example.myapplication.users.UserRepository
import com.example.myapplication.users.UserTask
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.fragment_menu_patient.*

class MenuPatientFragment : Fragment() {
    private var taskRepository = TasksRepository()
    private var userRepository = UserRepository()
    private var user = User()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userRepository.getCurrentUserMustExist {
                taskRepository.getActiveTasksByUser(it) {
                            createTaskList(it)
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_patient, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userRepository.getCurrentUserMustExist{ us ->
            idPatient.text = String.format("Witaj: %s %s", us.firstName, us.lastName)

            userRepository.getUserLambda(us.caregiver) {
                val number = it.phone


                call.setOnClickListener{
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(number)))
                    startActivity(intent)
                }
            }
        }

    }

    private fun createTaskList(tasks: List<UserTask>) {
        val recyclerView = requireView().findViewById<RecyclerView>(R.id.mainRecyclerView)

        val adapter = MenuPatientListAdapter(tasks)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter.onItemClick = {
            if (it.type.navigationId !== null) {
                findNavController().navigate(it.type.navigationId!!, bundleOf("userTask" to it))
            }
        }
        recyclerView.adapter = adapter
    }


}