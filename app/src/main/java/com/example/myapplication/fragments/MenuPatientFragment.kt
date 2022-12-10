package com.example.myapplication.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MenuPatientListAdapter
import com.example.myapplication.R
import com.example.myapplication.users.TasksRepository
import com.example.myapplication.users.User
import com.example.myapplication.users.UserRepository
import com.example.myapplication.users.UserTask
import com.google.firebase.firestore.QuerySnapshot

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 *
 * A simple [Fragment] subclass.
 * Use the [MenuPatientFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuPatientFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var taskRepository = TasksRepository()
    private var userRepository = UserRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        userRepository.getCurrentUser().addOnSuccessListener {
            if(it.exists()) {
                taskRepository.getTasksByUser(it.toObject(User::class.java)!!)
                    .addOnSuccessListener { list ->
                        if (!list.isEmpty) {
                            createTaskList(list)
                        }
                    }
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

    fun createTaskList(list: QuerySnapshot) {
        val recyclerView = requireView().findViewById<RecyclerView>(R.id.mainRecyclerView)
        val tasks = list.documents.mapNotNull { it.toObject(UserTask::class.java) }

        val adapter = MenuPatientListAdapter(tasks)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter.onItemClick = {
            if (it.type.navigationId !== null) {
                findNavController().navigate(it.type.navigationId!!)
            }
        }

        recyclerView.adapter = adapter

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MenuPatientFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MenuPatientFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}