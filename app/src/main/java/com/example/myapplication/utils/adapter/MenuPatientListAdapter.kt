package com.example.myapplication.utils.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.databinding.ListCardViewBinding
import com.example.myapplication.databinding.ListPatientsBinding
import com.example.myapplication.users.UserRepository
import com.example.myapplication.users.UserTask

class MenuPatientListAdapter(private val list: List<UserTask>):
    RecyclerView.Adapter<MenuPatientListAdapter.MenuPatientsListHolder>() {

    private val userRepository = UserRepository()
    private val userTask = UserTask()
    private  val TAG = "MenuPatientListAdapter"

    var onItemClick: ((UserTask) -> Unit)? = null


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuPatientsListHolder {
         val binding =
             ListCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return MenuPatientsListHolder(binding = binding)
        }

        // binds the list items to a view
        override fun onBindViewHolder(holder: MenuPatientsListHolder, position: Int) {

            with(holder){
                with(list[position]){
                    cardView.setOnClickListener{
                        onItemClick?.invoke(this)
                    }

                    todoTitle.text =this.type.title
                    taskIcon.setImageResource(this.type.icon)
                    todoDate.text = this.startDate.toDate().toInstant().toString()
                    todoPatient.text =this.description

                    userRepository.getUserLambda(this.uid) {
                        todoPatient.text = String.format("%s %s", it.firstName, it.lastName)
                    }

                    Log.d(TAG, "onBindViewHolder: "+this)

                }
            }
        }


        override fun getItemCount(): Int {
            return list.size
        }


         class MenuPatientsListHolder(binding: ListCardViewBinding) :
            RecyclerView.ViewHolder(binding.root) {

            val cardView = binding.cardView
            val todoTitle = binding.todoTitle
            val todoDate = binding.todoDate
            val taskIcon = binding.taskIcon
            val todoPatient = binding.todoPatient
        }
    }