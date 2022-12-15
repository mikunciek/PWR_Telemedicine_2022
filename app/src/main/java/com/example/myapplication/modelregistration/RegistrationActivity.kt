package com.example.myapplication.modelregistration

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.LoginActivity
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.users.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegistrationActivity : AppCompatActivity(), View.OnClickListener {
    private val REGISTRATION_DEBUG = "REGISTRATION_ACTIVITY_DEBUG"

    private val registrationVm: RegistrationViewModel by viewModels<RegistrationViewModel>()
    private val fbAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private var loginTextView: TextView? = null
    private var signUpButton: Button? = null

    private var usernameEditText : EditText? = null
    private var userLastNameEditText : EditText? = null
    private var emailEditText : EditText? = null
    private var phoneEditText : EditText? = null
    private var passwordEditText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        init()

        loginTextView?.setOnClickListener(this)
        signUpButton?.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.textGoToLogin -> setupLoginInClick()
            R.id.cirLoginButton -> setupSignUpClick()
        }
    }

    private fun setupLoginInClick() {
        val intent = Intent(applicationContext, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun setupSignUpClick() {
        val email: String = emailEditText!!.text?.trim().toString()
        val name: String = usernameEditText!!.text?.trim().toString()
        val lastName:String = userLastNameEditText!!.text?.trim().toString()
        val phone: String = phoneEditText!!.text?.trim().toString()
        val password: String = passwordEditText!!.text?.trim().toString()


        fun isEmpty(checkFiled: String, titleFiled:String): Boolean{
            if(checkFiled.isNotEmpty()){
                return true
            }
            Toast.makeText(applicationContext, "Wprowadź $titleFiled", Toast.LENGTH_SHORT).show()
            return false
        }

        if ( isEmpty(name, "imię") && isEmpty(lastName, "nazwisko") &&
            isEmpty(email, "email") && isEmpty(phone, "telefon")
            && isEmpty(password, "hasło")) {
            fbAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener { authRes ->

                    if (authRes.user != null) {
                        val user = User(
                            authRes.user!!.uid,
                            firstName = name,
                            lastName = lastName,
                            email = email,
                            phone = phone
                        )

                        registrationVm.createNewMainUser(user)
                        val intent: Intent =
                            Intent(applicationContext, MainActivity::class.java).apply {
                                flags = (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            }
                        startActivity(intent)
                    }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(applicationContext, "Rejestracja nieudana", Toast.LENGTH_SHORT).show()
                    Log.d(REGISTRATION_DEBUG, exception.message.toString())
                }
        } else {
            Toast.makeText(applicationContext, "Rejestracja udana", Toast.LENGTH_SHORT).show()
        }
    }

    private fun init() {
        loginTextView = textGoToLogin
        signUpButton = cirLoginButton
        usernameEditText = editTextName
        userLastNameEditText = editLastName
        emailEditText = editTextEmail
        phoneEditText = exitTextPhone
        passwordEditText = editTextPassword
    }
}

