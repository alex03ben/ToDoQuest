package com.alexb.todoquest.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alexb.todoquest.R
import com.alexb.todoquest.view.fragments.TaskListFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Replace the default fragment with TaskListFragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_list_task, TaskListFragment())
            .commit()
    }
}