package com.example.myapplication.utils.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.myapplication.databinding.ListTaskStatusBinding
import com.example.myapplication.users.TasksRepository
import com.example.myapplication.users.UserRepository
import com.example.myapplication.users.UserTask


class TaskStatusAdapter (private val list: MutableList<UserTask>)
    :RecyclerView.Adapter<TaskStatusAdapter.TaskStatusViewHolder>() {

    private val tasksRepository = TasksRepository()
    private val userRepository = UserRepository()
    private val TAG = "TaskStatusAdapter"

    var onItemClick :((UserTask) -> Unit)? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskStatusViewHolder {
        val binding =
            ListTaskStatusBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskStatusViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskStatusAdapter.TaskStatusViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {

                userRepository.getUserLambda(this.user) {
                    fullName.text = String.format("%s %s", it.firstName, it.lastName)
                }

                taskNameTask.text =  this.type.title
                startTask.text = this.startDate.toDate().toInstant().toString()
                endTask.text = this.closeDate!!.toDate().toInstant().toString()
                statusTask.text=this.status.toString()
                resultTask.text=this.result


                Log.d(TAG, "onBindViewHolder: "+this)

            }
        }
    }





    interface TaskStatusAdapterInterface{
        fun onDeleteItemClicked(userTask: UserTask, position : Int)
    }

    override fun getItemCount(): Int {  //liczba zadań w liście
        return list.size
    }


    class TaskStatusViewHolder(val binding: ListTaskStatusBinding)
        : RecyclerView.ViewHolder(binding.root){

        val fullName = binding.fullName
        val taskNameTask = binding.taskName
        val startTask = binding.startTask
        val endTask = binding.endTask
        val statusTask = binding.statusTask
        val resultTask = binding.resultTask
        }
}