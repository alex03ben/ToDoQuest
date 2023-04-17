package com.alexb.todoquest.model.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alexb.todoquest.model.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TodoQuestDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: TodoQuestDatabase? = null

        fun getDatabase(context: Context): TodoQuestDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoQuestDatabase::class.java,
                    "todoquest_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}