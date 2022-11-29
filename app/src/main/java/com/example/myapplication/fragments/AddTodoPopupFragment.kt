package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentAddTodoPopupBinding
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_add_todo_popup.view.*

class AddTodoPopupFragment : DialogFragment() {

    private lateinit var binding:FragmentAddTodoPopupBinding
    private lateinit var listener : DialogNextBtnClickListeners
    
    fun setListener(listener : DialogNextBtnClickListeners){
        this.listener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddTodoPopupBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerEvents()
    }

    private fun registerEvents(){
        binding.todoNextBtn.setOnClickListener{
            val todoTask = binding.todoEt.text.toString()

            if(todoTask.isNotEmpty()){
                listener.onSaveTask(todoTask,binding.todoEt)

            }else{
                Toast.makeText(context, "Puste zadanie",Toast.LENGTH_SHORT).show()
            }
        }
    }

    interface DialogNextBtnClickListeners{
        fun onSaveTask(todo : String, todoEt : TextInputEditText)
    }
}