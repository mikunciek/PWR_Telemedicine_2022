package com.example.myapplication.fragments

import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentPatientsBinding
import com.example.myapplication.databinding.ListPatientsBinding
import com.example.myapplication.fragments.ToDoDialogFragment.Companion.TAG
import com.example.myapplication.users.User
import com.example.myapplication.users.UserRepository

import com.example.myapplication.utils.adapter.PatientsAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_patients.*
import kotlinx.android.synthetic.main.list_patients.*

class PatientsFragment : Fragment() {

    private val userRepository = UserRepository()
    private lateinit var binding: FragmentPatientsBinding
    private lateinit var database: DatabaseReference

    private lateinit var buldier : AlertDialog.Builder

    private lateinit var bindListView: ListPatientsBinding

    private lateinit var button: ImageButton



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

        buldier = AlertDialog.Builder(requireContext())


        button = deleteUser

/*
            button.setOnClickListener{
                buldier
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Usuwanie pacjenta")
                .setMessage("Czy chcesz usunąć pacjenta z listy użytkowników?")
                .setCancelable(true) //dialog box in cancellable
                .setPositiveButton("Tak") {dialogInterface, it ->
                    userRepository.deletePatients()
                }
                //{ dialog: DialogInterface?, which: Int -> userRepository.deletePatients()}

                    //usunięcie użytkownika

                .setNegativeButton("Nie"){dialogInterface, it ->dialogInterface.cancel() }
                .show()

            }

 */


        }





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

    private fun init() {
        userRepository.getCurrentUserPatients {
            patientsList = mutableListOf()

            patientsList.clear()
            patientsList.addAll(it)


            binding.mainRecyclerView.setHasFixedSize(true)
            binding.mainRecyclerView.layoutManager = LinearLayoutManager(context)
            patientsAdapter = PatientsAdapter(patientsList)
            binding.mainRecyclerView.adapter = patientsAdapter

            countPatients.text = String.format("Ilość Twoich pacjentów: %s ", patientsAdapter.itemCount.toString())


        }
    }


}