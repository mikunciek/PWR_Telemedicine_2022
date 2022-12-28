package com.example.myapplication.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentNewUserBinding
import com.example.myapplication.users.User
import com.example.myapplication.users.UserRepository
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.fragment_new_user.*
import java.util.UUID

class NewUserFragment : Fragment() {

    private val REGISTRATION_DEBUG = "REGISTRATION_ACTIVITY_DEBUG"

    private val userRepository = UserRepository()
    private lateinit var binding: FragmentNewUserBinding

    private var registerButton: Button? = null

    private var firstNameEditText : EditText? = null
    private var lastNameEditText : EditText? = null
    private var emailEditText : EditText? = null
    private var phoneEditText : EditText? = null
    private var passwordEditText: EditText? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //TODO: Inicjalizacja zmiennych, analogicznie jak w RegistrationActivity.kt
        init()
        binding.saveNewUser.setOnClickListener {
            registerButtonClick()
        }

       /* var save = true
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

        */
    }


    fun isNotEmpty(checkFiled: String, titleFiled:String): Boolean{
        if(checkFiled.isNotEmpty()){
            return true
        }
        Toast.makeText(requireContext(), "Wprowadź $titleFiled", Toast.LENGTH_SHORT).show()
        return false
    }

    private fun registerButtonClick(){
        //TODO: Dokończyć funkcję, by zapis był dozwolony gdy pole nie jest puste

        val name: String = firstNameEditText!!.text?.trim().toString()
        val lastName:String = lastNameEditText!!.text?.trim().toString()
        val email: String = emailEditText!!.text?.trim().toString()
        val phone: String = phoneEditText!!.text?.trim().toString()
        val password: String = passwordEditText!!.text?.trim().toString()

        if ( isNotEmpty(name, "imię") && isNotEmpty(lastName, "nazwisko")
            && isNotEmpty(email, "email") && isNotEmpty(phone, "telefon")
            && isNotEmpty(password, "hasło")) {

                userRepository.getCurrentUserMustExist {
                UserRepository.auth.createUserWithEmailAndPassword(email,password)
                    .addOnSuccessListener { auth ->
                        if(auth.user !=null) {
                            val user = User(
                                uid = UUID.randomUUID().toString(),
                                caregiver = it.uid,
                                firstName = binding.inputFirstName.text.toString(),
                                lastName = binding.inputLastName.text.toString(),
                                phone = binding.inputPhone.text.toString(),
                                email = binding.inputEmailText.text.toString()
                            )
                            userRepository.save(user)

                        }
                        Toast.makeText(requireContext(), "Dodano nowego użytkownika", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_newUserFragment_to_menuCaregiverFragment)
                    }
                    .addOnFailureListener { exception ->
                        Toast.makeText(requireContext(), "Rejestracja nieudana: ${exception.message}", Toast.LENGTH_SHORT).show()
                        Log.d(REGISTRATION_DEBUG, exception.message.toString())
                    }
            }
        }
    }

    private fun init(){
        registerButton = saveNewUser
        firstNameEditText =inputFirstName
        lastNameEditText = inputLastName
        emailEditText = inputEmailText
        phoneEditText = inputPhone
        passwordEditText = inputPassword

    }
}