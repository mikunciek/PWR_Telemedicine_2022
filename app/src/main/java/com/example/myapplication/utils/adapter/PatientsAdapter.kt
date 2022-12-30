package com.example.myapplication.utils.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ListPatientsBinding
import com.example.myapplication.databinding.ListPatientsBinding.*
import com.example.myapplication.users.TasksRepository
import com.example.myapplication.users.User
import com.example.myapplication.users.UserRepository
import kotlinx.coroutines.runBlocking

class PatientsAdapter(private val list: MutableList<User>):
    RecyclerView.Adapter<PatientsAdapter.PatientsViewHolder>() {

    private val taskRepository = TasksRepository()
    private val userRepository = UserRepository()
    private val TAG = "PatientsAdapter"

    var onItemClick : ((User) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientsViewHolder {
        val binding =
            inflate(LayoutInflater.from(parent.context), parent, false)
        return PatientsViewHolder(binding)
    }


    override fun onBindViewHolder(holder: PatientsViewHolder, position: Int) {
          with(holder) {
            with(list[position]) {
                //przycisk
                deleteBtn.setOnClickListener {
                    onItemClick?.invoke(this)
                }

                val user = this
                runBlocking {
                    val doneCount = taskRepository.getCountOfDoneTasks(user)
                    val todoCount = taskRepository.getCountOfTODOTasks(user)

                    //ilośc zadań
                    status.text = (todoCount + doneCount).toString()
                    //wykonanych
                    statusDone.text = doneCount.toString()
                    //niewykonanych
                    statusNoDone.text = todoCount.toString()
                    //nazwa pacjenta

                }


                userRepository.getUserLambda(this.uid) {
                   patientTitle.text = String.format("%s %s", it.firstName, it.lastName)
                }
                Log.d(TAG, "onBindViewHolder: "+this)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class PatientsViewHolder(val binding: ListPatientsBinding):
        RecyclerView.ViewHolder(binding.root){

        val patientTitle = binding.pacjentTitle
        val status = binding.status
        val statusDone = binding.statusDone
        val statusNoDone = binding.statusNoDone
        val deleteBtn = binding.deleteUser
        }
    }



