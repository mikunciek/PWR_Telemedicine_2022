package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.example.myapplication.users.UserTask

class MenuPatientListAdapter(private val mList: List<UserTask>): RecyclerView.Adapter<MenuPatientListAdapter.ViewHolder>() {

    var onItemClick: ((UserTask) -> Unit)? = null

        // create new views
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            // inflates the card_view_design view
            // that is used to hold list item
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_card_view, parent, false)

            return ViewHolder(view)
        }

        // binds the list items to a view
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            val userTask: UserTask = mList[position]

            holder.image.setImageResource(userTask.type.icon)
            holder.title.text = userTask.type.title
            holder.description.text = userTask.description

        }

        // return the number of the items in the list
        override fun getItemCount(): Int {
            return mList.size
        }

        // Holds the views for adding it to image and text
        inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
            val image: ImageView = itemView.findViewById(R.id.task_icon)
            val title: TextView = itemView.findViewById(R.id.task_title)
            val description: TextView = itemView.findViewById(R.id.task_description)

            init {
                itemView.setOnClickListener {
                    onItemClick?.invoke(mList[bindingAdapterPosition])
                }
            }
        }
    }