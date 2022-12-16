package com.example.myapplication.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentPatientsBinding
import com.example.myapplication.databinding.FragmentTodoCaregiverBinding
import com.example.myapplication.fragments.ToDoDialogFragment.Companion.TAG
import com.example.myapplication.users.User
import com.example.myapplication.users.UserRepository
import com.example.myapplication.users.UserTask
import com.example.myapplication.utils.adapter.PatientsAdapter
import com.example.myapplication.utils.adapter.TaskAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

class PatientsFragment : Fragment() {

    private val userRepository = UserRepository()
    private lateinit var binding: FragmentPatientsBinding
    private lateinit var database: DatabaseReference

    private lateinit var auth: FirebaseAuth //autoryzacja z firebase
    private lateinit var authId: String  //id

    private lateinit var patientsAdapter: PatientsAdapter  //zadania
    private lateinit var patientsList: MutableList<User>  //lista zadań

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPatientsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        //getListPatientsFromFirebase()
        userRepository.getCurrentUserPatients {
        }
    }

/*
    private fun getListPatientsFromFirebase(){
        database.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                patientsList.clear()
                for (patientsSnaphot in snapshot.children){
                    val patients = patientsSnaphot.key?.let { User(it,
                    patientsSnaphot.value.toString()) }

                    if(patients !=null){
                        patientsList.add(patients)
                    }
                }
                Log.d(TAG, "onDataChange: "+ patientsList)
                patientsAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }


 */


    private fun init() {

        binding.mainRecyclerView.setHasFixedSize(true)
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(context)
        patientsList = mutableListOf()
        patientsAdapter = PatientsAdapter(patientsList)
        binding.mainRecyclerView.adapter = patientsAdapter

    }
}