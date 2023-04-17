package com.alexb.todoquest.view.fragments

import android.os.Bundle
import android.os.Debug
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
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

        // Set up RecyclerView with TaskAdapter
        val recyclerView = view.findViewById<RecyclerView>(R.id.task_list)
        val adapter = TaskAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        // Get TaskViewModel from ViewModelProvider
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        // Observe all tasks in TaskViewModel and update RecyclerView
        taskViewModel.allTasks.observe(viewLifecycleOwner, { tasks ->
            tasks?.let { adapter.setTasks(it) }
        })

        // Set up Add Task button to navigate to AddTaskFragment
        val addTaskButton = view.findViewById<FloatingActionButton>(R.id.add_task_button)
        addTaskButton.setOnClickListener {
            //findNavController().navigate(R.id.action_)
            Log.d("TEST", "========== ICI TEST ===============")
            taskViewModel.insert(Task("test", "testest", 5000, true))
            Log.d("TEST", "========== nb =============== "+adapter.itemCount)
        }

        val deleteAllTaskButton = view.findViewById<FloatingActionButton>(R.id.delete_task_button)
        deleteAllTaskButton.setOnClickListener(){
            taskViewModel.deleteAll()
        }

        return view
    }
}