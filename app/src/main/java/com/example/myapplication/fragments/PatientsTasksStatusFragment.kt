package com.example.myapplication.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentPatientsTasksStatusBinding
import com.example.myapplication.users.TaskStatus
import com.example.myapplication.users.TasksRepository
import com.example.myapplication.users.UserTask
import com.example.myapplication.utils.adapter.TaskStatusAdapter


class PatientsTasksStatusFragment : Fragment(),
      TaskStatusAdapter.TaskStatusAdapterInterface {

    private val tasksRepository =TasksRepository()
    private lateinit var binding:FragmentPatientsTasksStatusBinding
    private lateinit var patientsTasksStatusAdapter: TaskStatusAdapter
    private lateinit var statusList:MutableList<UserTask>

    private val TAG = "PatientsTasksStatusFragment"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPatientsTasksStatusBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        getStatusTaskFromFirebase()

    }

    private fun init() {
        statusList = mutableListOf()

        binding.mainRecyclerView.setHasFixedSize(true)
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(context)

        patientsTasksStatusAdapter = TaskStatusAdapter(statusList)
        binding.mainRecyclerView.adapter =patientsTasksStatusAdapter
    }

    private fun getStatusTaskFromFirebase() {
        tasksRepository.addSnapshotListenerForPatients {
            statusList.clear()
            statusList.addAll(it)

            Log.d(TAG, "onDataChange: " + statusList)
            patientsTasksStatusAdapter.notifyDataSetChanged()
        }
    }




    override fun onDeleteItemClicked(userTask: UserTask, position: Int) {
    }


}