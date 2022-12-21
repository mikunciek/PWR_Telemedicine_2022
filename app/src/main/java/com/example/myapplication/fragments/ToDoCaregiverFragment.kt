package com.example.myapplication.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentTodoCaregiverBinding
import com.example.myapplication.users.TasksRepository
import com.example.myapplication.users.UserTask
import com.example.myapplication.utils.adapter.TaskAdapter

class ToDoCaregiverFragment : Fragment(),
    TaskAdapter.TaskAdapterInterface {

    private val TAG = "ToDoCaregiverFragment"
    private lateinit var binding: FragmentTodoCaregiverBinding //tworzenie widoku
    private var frag: ToDoDialogFragment? = null
    private val tasksRepository = TasksRepository()


    private lateinit var taskAdapter: TaskAdapter  //zadania
    private lateinit var toDoItemList: MutableList<UserTask>  //lista zadań

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? //metoda, która inicjuje i tworzy wszystkie obiekty
    ): View? {

        binding = FragmentTodoCaregiverBinding.inflate(inflater, container, false)
        return binding.root
    }
        // Inflate the layout for this fragment - załadowanie konkretnego widoku
        /*
        binding root - odniesieniem do widoku głównego.
        widok główny to najbardziej zewnętrzny kontener widoku w tym układzie.
        wywołanie binding.root , zwróci widok główny ConstraintLayout (w tym przypadku)
         */


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {  //właściwe wyświetlanie
        super.onViewCreated(view, savedInstanceState)
        init()   //funkcja - inicjowanie z firebase (typy danych)
        getTaskFromFirebase()
        binding.addTaskBtn.setOnClickListener { //gdy naciśnięcie przycisku dodawania zadania
            if (frag != null)
                childFragmentManager.beginTransaction().remove(frag!!).commit() //mamy wycinek fragmentu - stąd child
            frag = ToDoDialogFragment() //okienko zadania
            frag!!.show(childFragmentManager,ToDoDialogFragment.TAG) //wyświetlenie

        }
    }

    private fun getTaskFromFirebase() {  //pobieranie zadań z Firebase
        tasksRepository.addSnapshotListenerForPatients {
            toDoItemList.clear()
            toDoItemList.addAll(it)

            Log.d(TAG, "onDataChange: " + toDoItemList)
            taskAdapter.notifyDataSetChanged()
        }
    }


    private fun init() { //inicjowanie z bazy zadań
            toDoItemList = mutableListOf()

            binding.mainRecyclerView.setHasFixedSize(true)
            binding.mainRecyclerView.layoutManager = LinearLayoutManager(context)

            taskAdapter = TaskAdapter(toDoItemList)
            taskAdapter.setListener(this)
            binding.mainRecyclerView.adapter = taskAdapter
    }



    override fun onDeleteItemClicked(toDoData: UserTask, position: Int) { //usuwanie
    }
    }



