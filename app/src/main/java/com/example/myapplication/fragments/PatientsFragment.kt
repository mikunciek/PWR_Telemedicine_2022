package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentPatientsBinding
import com.example.myapplication.users.User
import com.example.myapplication.users.UserRepository

import com.example.myapplication.utils.adapter.PatientsAdapter
import kotlinx.android.synthetic.main.fragment_patients.*

class PatientsFragment : Fragment() {

    private val userRepository = UserRepository()
    private lateinit var binding: FragmentPatientsBinding
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
        getDataFromFirebase()


        patientsAdapter.onItemClick = {
            deletePatients(it)
        }
    }

    private fun getDataFromFirebase() {
        userRepository.addSnapshotEventForPatients {
            patientsList.clear()
            patientsList.addAll(it)

            patientsAdapter.notifyDataSetChanged()
            countPatients.text = String.format("Ilość Twoich pacjentów: %s ", patientsAdapter.itemCount.toString())
           }
    }

    private fun init() {
            patientsList = mutableListOf()
            patientsList.clear()

            binding.mainRecyclerView.setHasFixedSize(true)
            binding.mainRecyclerView.layoutManager = LinearLayoutManager(context)
            patientsAdapter = PatientsAdapter(patientsList)
            binding.mainRecyclerView.adapter = patientsAdapter
    }

    private fun deletePatients(user: User){
        AlertDialog.Builder(requireContext())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Usuwanie pacjenta")
                .setMessage("Czy chcesz usunąć pacjenta z listy użytkowników?")
                .setCancelable(true) //dialog box in cancellable
                .setPositiveButton("Tak") {DialogInterface, it ->
                    userRepository.deletePatients(user);
                    Toast.makeText(requireContext(), "Usunięto użytkownika-pacjenta", Toast.LENGTH_SHORT).show()

                }
                .setNegativeButton("Nie"){dialogInterface, it ->
                    dialogInterface.cancel()
                    Toast.makeText(requireContext(), "Anulowano usuwanie użytkownika - pacjenta", Toast.LENGTH_SHORT).show()}
                .show()
        }
    }

