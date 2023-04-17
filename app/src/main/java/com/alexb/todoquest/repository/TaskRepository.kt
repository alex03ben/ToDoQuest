package com.alexb.todoquest.repository

import androidx.lifecycle.LiveData
import com.alexb.todoquest.model.Task
import com.alexb.todoquest.model.dao.TaskDao

class TaskRepository(private val taskDao: TaskDao) {
    val allTasks: LiveData<List<Task>> = taskDao.getAllTasks()

    suspend fun insert(task: Task) {
        taskDao.insertTask(task)
    }

    suspend fun update(task: Task) {
        taskDao.updateTask(task)
    }

    suspend fun delete(task: Task) {
        taskDao.deleteTaskById(task.id)
    }

    suspend fun deleteAll() {
        taskDao.deleteAllTasks()
    }
}