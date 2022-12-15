package com.example.myapplication.utils.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.EachTodoItemBinding
import com.example.myapplication.users.User
import com.example.myapplication.users.UserRepository
import com.example.myapplication.users.UserTask


class TaskAdapter(private val list: MutableList<UserTask>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private val userRepository = UserRepository()
    private  val TAG = "TaskAdapter"
    private var listener:TaskAdapterInterface? = null
    fun setListener(listener:TaskAdapterInterface){
        this.listener = listener
    }

    class TaskViewHolder(val binding: EachTodoItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding =
            EachTodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) { //nagłówek???
        with(holder) {
            with(list[position]) {
                binding.todoTitle.text = this.type.title
                binding.taskIcon.setImageResource(this.type.icon)

                userRepository.getUserLambda(this.uid) {
                        binding.todoPatient.text = String.format("%s %s", it.firstName, it.lastName)
                }

                Log.d(TAG, "onBindViewHolder: "+this)

                binding.deleteTask.setOnClickListener {  //przycisk
                    listener?.onDeleteItemClicked(this , position)
                }
            }
        }
    }

    override fun getItemCount(): Int {  //liczba zadań w liście
        return list.size
    }

    interface TaskAdapterInterface{
        fun onDeleteItemClicked(userTask: UserTask , position : Int)
    }

}