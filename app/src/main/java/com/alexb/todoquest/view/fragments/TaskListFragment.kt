package com.alexb.todoquest.view.fragments

import android.os.Bundle
import android.os.Debug
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexb.todoquest.R
import com.alexb.todoquest.adapter.TaskAdapter
import com.alexb.todoquest.model.Task
import com.alexb.todoquest.viewmodel.TaskViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A simple [Fragment] subclass.
 * Use the [TaskListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TaskListFragment : Fragment() {

    private lateinit var taskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task_list, container, false)

        //Parametre RecyclerView avec TaskAdapter
        val recyclerView = view.findViewById<RecyclerView>(R.id.task_list)
        val adapter = TaskAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        //Recupere le ViewModel
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        //Observe toutes les taches dans le ViewModel et met a jour le RecyclerView
        taskViewModel.allTasks.observe(viewLifecycleOwner, { tasks ->
            tasks?.let { adapter.setTasks(it) }
        })

        //Parametre le bouton pour ajouter une tache
        val addTaskButton = activity?.findViewById<FloatingActionButton>(R.id.add_task_button)
        addTaskButton?.setOnClickListener {
            //findNavController().navigate(R.id.action_)
            Log.d("TEST", "========== ICI TEST ===============")
            taskViewModel.insert(Task("test", "testest", 5000, true))
            Log.d("TEST", "========== nb =============== " + adapter.itemCount)
        }

        /*val deleteAllTaskButton =
            activity?.findViewById<FloatingActionButton>(R.id.delete_task_button)
        deleteAllTaskButton?.setOnClickListener() {
            taskViewModel.deleteAll()
        }*/

        val itemTouchHelperCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val task = adapter.getTasksAt(viewHolder.adapterPosition)
                taskViewModel.delete(task)
                Toast.makeText(activity, "Task deleted", Toast.LENGTH_SHORT).show()
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        return view
    }
}