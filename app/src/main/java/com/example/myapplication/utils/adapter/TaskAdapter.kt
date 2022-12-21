package com.example.myapplication.utils.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.EachTodoItemBinding
import com.example.myapplication.users.TasksRepository
import com.example.myapplication.users.User
import com.example.myapplication.users.UserRepository
import com.example.myapplication.users.UserTask


class TaskAdapter(private val list: MutableList<UserTask>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private val tasksRepository = TasksRepository()
    private val userRepository = UserRepository()
    private  val TAG = "TaskAdapter"
    private var listener:TaskAdapterInterface? = null
    fun setListener(listener:TaskAdapterInterface){
        this.listener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding =
            EachTodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                binding.todoTitle.text = this.type.title
                binding.taskIcon.setImageResource(this.type.icon)

                userRepository.getUserLambda(this.uid) {
                        binding.todoPatient.text = String.format("%s %s", it.firstName, it.lastName)
                }
                Log.d(TAG, "onBindViewHolder: "+this)
            }
        }
    }


    interface TaskAdapterInterface{
        fun onDeleteItemClicked(userTask: UserTask , position : Int)
    }

    override fun getItemCount(): Int {  //liczba zadań w liście
        return list.size
    }


    class TaskViewHolder(val binding: EachTodoItemBinding) : RecyclerView.ViewHolder(binding.root){
        val icon = binding.taskIcon
        val todoTitle = binding.todoTitle
        val todoPatient = binding.todoPatient
        val todoDate = binding.todoDate
    }


}