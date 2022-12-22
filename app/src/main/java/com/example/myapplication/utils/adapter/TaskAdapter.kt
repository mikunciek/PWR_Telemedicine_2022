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

    private lateinit var mListener : onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener =listener
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding =
            EachTodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding, mListener)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                todoTitle.text = this.type.title
                taskIcon.setImageResource(this.type.icon)
                todoDate.text = this.startDate.toDate().toInstant().toString()
                userRepository.getUserLambda(this.user) {
                        todoPatient.text = String.format("%s %s", it.firstName, it.lastName)
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


    class TaskViewHolder(val binding: EachTodoItemBinding, mListener:onItemClickListener) : RecyclerView.ViewHolder(binding.root) {


        val todoTitle = binding.todoTitle
        val taskIcon = binding.taskIcon
        val todoDate = binding.todoDate
        val todoPatient = binding.todoPatient

        init {
            itemView.setOnClickListener {
                mListener.onItemClick(adapterPosition)
            }
        }

    }
}