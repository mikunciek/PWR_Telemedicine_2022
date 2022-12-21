package com.example.myapplication.utils.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ListPatientsBinding
import com.example.myapplication.databinding.ListPatientsBinding.*
import com.example.myapplication.users.User
import com.example.myapplication.users.UserRepository

class PatientsAdapter(private val list: MutableList<User>):
    RecyclerView.Adapter<PatientsAdapter.PatientsViewHolder>() {

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
                //TODO: fun liczące ilość zadań i przypisujące do tych pól po użytkowniku
                //ilośc zadań
                status.text = "0"
               //wykonanych
                statusDone.text = "0"
               //niewykonanych
                statusNoDone.text = "0"
                //nazwa pacjenta

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


    class PatientsViewHolder(val binding: ListPatientsBinding):RecyclerView.ViewHolder(binding.root){

        val patientTitle = binding.pacjentTitle
        val status = binding.status
        val statusDone = binding.statusDone
        val statusNoDone = binding.statusNoDone
        val deleteBtn = binding.deleteUser
        }
    }



