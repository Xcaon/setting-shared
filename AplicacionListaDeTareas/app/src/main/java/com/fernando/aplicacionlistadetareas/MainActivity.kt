package com.fernando.aplicacionlistadetareas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    // Le pasamos un listado de tareas que hemos hecho "sealed", es decir, con x atributos
    private val categories = listOf(
        TaskCategory.Business,
        TaskCategory.Personal,
        TaskCategory.Other
    )

    private val tasks = mutableListOf(
        Task("PruebaBusiness", TaskCategory.Business),
        Task("PruebaBusiness", TaskCategory.Personal),
        Task("PruebaBusiness", TaskCategory.Other)
    )

    private lateinit var rvCategories: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter

    private lateinit var rvTasks:RecyclerView
    private lateinit var tasksAdapter: TasksAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponent()
        initUI()
    }

    private fun initUI() {
        categoriesAdapter = CategoriesAdapter(categories)
        rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        rvCategories.adapter = categoriesAdapter

        tasksAdapter = TasksAdapter(tasks)
        // Por defecto es Vertical, por lo tanto lo dejamos asi
        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = tasksAdapter

    }

    private fun initComponent() {
        rvCategories = findViewById(R.id.rvCategories)
        rvTasks = findViewById(R.id.rvTasks)
    }


}