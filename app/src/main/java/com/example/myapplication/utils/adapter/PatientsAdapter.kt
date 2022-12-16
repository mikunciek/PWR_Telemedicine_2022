package com.example.myapplication.utils.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ListPatientsBinding
import com.example.myapplication.users.User
import com.example.myapplication.users.UserRepository
import com.example.myapplication.users.UserTask

class PatientsAdapter(private val list: MutableList<User>): RecyclerView.Adapter<PatientsAdapter.PatientsViewHolder>() {

    private val userRepository = UserRepository()
    private val TAG = "PatientsAdapter"



    class PatientsViewHolder(val binding: ListPatientsBinding):RecyclerView.ViewHolder(binding.root)




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientsViewHolder {
        val binding =
            ListPatientsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PatientsAdapter.PatientsViewHolder(binding)
    }


    override fun onBindViewHolder(holder: PatientsAdapter.PatientsViewHolder, position: Int) { //nagłówek???
        with(holder) {
            with(list[position]) {
               //ilośc zadań
                binding.status.text =itemCount.toString()
               //wykonanych
               //niewykonanych

                userRepository.getUserLambda(this.uid) {
                   binding.pacjentTitle.text = String.format("%s %s", it.firstName, it.lastName)
                }

                Log.d(TAG, "onBindViewHolder: "+this)


            }
        }
    }
// TODO skończyć adapter

    override fun getItemCount(): Int {  //liczba zadań w liście
        return list.size
    }

}

