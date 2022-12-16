package com.example.myapplication.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentTodoCaregiverBinding
import com.example.myapplication.users.UserTask
import com.example.myapplication.utils.adapter.TaskAdapter
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import java.time.LocalDateTime

class ToDoCaregiverFragment : Fragment(), //ToDoDialogFragment.OnDialogNextBtnClickListener,
    TaskAdapter.TaskAdapterInterface {

    private val TAG = "ToDoCaregiverFragment"
    private lateinit var binding: FragmentTodoCaregiverBinding //tworzenie widoku
    private lateinit var database: DatabaseReference   //połączenie z bazą
    private var frag: ToDoDialogFragment? = null
    private lateinit var auth: FirebaseAuth //autoryzacja z firebase
    private lateinit var authId: String  //id



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
//        getTaskFromFirebase() //pobieranie danych z firebase

        binding.addTaskBtn.setOnClickListener { //gdy naciśnięcie przycisku dodawania zadania
            if (frag != null)
                childFragmentManager.beginTransaction().remove(frag!!).commit() //mamy wycinek fragmentu - stąd child
            frag = ToDoDialogFragment() //okienko zadania
            //frag!!.setListener(this)
            frag!!.show(childFragmentManager,ToDoDialogFragment.TAG) //wyświetlenie

        }
    }

    private fun getTaskFromFirebase() {  //pobieranie zadań z Firebase
        database.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                toDoItemList.clear()
                for (taskSnapshot in snapshot.children) {
                    val todoTask = taskSnapshot.key?.let { UserTask(it, taskSnapshot.value.toString()) }

                    if (todoTask != null) {
                        toDoItemList.add(todoTask)
                    }
                }

                Log.d(TAG, "onDataChange: " + toDoItemList)
                taskAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) { //anulowanie zadania
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun init() { //inicjowanie do bazy zadań
//        auth = FirebaseAuth.getInstance()
//        authId = auth.currentUser!!.uid
//        database = Firebase.database.reference.child("Tasks").child(authId)
        binding.mainRecyclerView.setHasFixedSize(true)
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(context)

        toDoItemList = mutableListOf()
        taskAdapter = TaskAdapter(toDoItemList)
        taskAdapter.setListener(this)
        binding.mainRecyclerView.adapter = taskAdapter
    }

    fun saveTask(todoTask: String, todoEdit: TextInputEditText) { //zapisywanie zadań
        todoEdit.text = null
        toDoItemList.add(UserTask(LocalDateTime.now().toString(), todoTask))
        frag!!.dismiss()
    }



    override fun onDeleteItemClicked(toDoData: UserTask, position: Int) { //usuwanie
    }

    /*

    fun updateTask(toDoData: UserTask, todoEdit: TextInputEditText) { //aktualizowanie zadań
        val map = HashMap<String, Any>() //mapa tasków
        map[toDoData.taskId] = toDoData.task
    }


    override fun onEditItemClicked(toDoData: UserTask, position: Int) { //edycja zadań
        if (frag != null)
            childFragmentManager.beginTransaction().remove(frag!!).commit()

        this.frag = ToDoDialogFragment.newInstance(toDoData.taskId, toDoData.task)
        frag!!.setListener(this)
        frag!!.show(childFragmentManager, ToDoDialogFragment.TAG)
    }

     */
}