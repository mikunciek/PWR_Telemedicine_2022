package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentNewUserBinding
import com.example.myapplication.users.User
import com.example.myapplication.users.UserRepository
import java.util.UUID

class NewUserFragment : Fragment() {

    private val userRepository = UserRepository()
    private lateinit var binding: FragmentNewUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var save = true
        binding.saveNewUser.setOnClickListener {
            userRepository.getCurrentUserMustExist {
                val user = User(
                    uid = UUID.randomUUID().toString(),
                    caregiver = it.uid,
                    firstName = binding.inputFirstName.text.toString(),
                    lastName = binding.inputLastName.text.toString(),
                    phone = binding.inputPhone.text.toString(),
                    email = binding.inputEmailText.text.toString()
                )

                if (save) {
                    userRepository.save(user)
                    UserRepository.auth.createUserWithEmailAndPassword(binding.inputEmailText.text.toString(), binding.inputPassword.text.toString()).addOnSuccessListener {
                        save = false
                        Toast.makeText(requireContext(), "Dodano nowego użytkownika", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_newUserFragment_to_menuCaregiverFragment)
                    }
                }
            }
        }
    }
}