package com.fernando.aplicacionlistadetareas

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

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

    private lateinit var fabAddTask: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponent()
        initUI()
        initListeners()
    }

    private fun initListeners() {
        fabAddTask.setOnClickListener {
            showDialog()
        }

    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_task)
        dialog.show()
        // Declaramos el boton del dialogo
        val etTask = dialog.findViewById<EditText>(R.id.etTask)
        val btAddTask = dialog.findViewById<Button>(R.id.btAddTask)
        val rgTask = dialog.findViewById<RadioGroup>(R.id.rgTask)

        btAddTask.setOnClickListener {

            val currentTask = etTask.text.toString()

            if ( currentTask.isNotEmpty()) {

                // Nos dara el id del RabioButton seleccinado
                val selectId = rgTask.checkedRadioButtonId
                // Buscamos el id seleccionado y lo asignamos a un boton
                val selectedRadioButton = rgTask.findViewById<RadioButton>(selectId)
                // Recoger el texto que contenga ese RadioButton
                // Tambien en vez de string podemos coger desde el xml de string con getString(R.string."nombre del string")
                val currentCategory: TaskCategory = when (selectedRadioButton.text) {
                    "Negocios" -> TaskCategory.Business
                    "Personal" -> TaskCategory.Personal
                    else -> TaskCategory.Other
                }

                // Añadir la informacion al array de objetos "tasks"
                tasks.add(Task(currentTask, currentCategory))
                // Avisamos al adapter de que hemos actualizado los datos
                updateTasks()

                // Escondemos el dialog
                dialog.hide()
            }
        }

    }

    private fun initUI() {
        categoriesAdapter = CategoriesAdapter(categories) { position -> updateCategories(position)}
        rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        rvCategories.adapter = categoriesAdapter

        // Hace la llamada a la funcion landa y cuando le devuelve, llama al metodo onItemSelected
        tasksAdapter = TasksAdapter(tasks) {position -> onItemSelected(position)}


        // Por defecto es Vertical, por lo tanto lo dejamos asi
        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = tasksAdapter

    }

    private fun initComponent() {
        rvCategories = findViewById(R.id.rvCategories)
        rvTasks = findViewById(R.id.rvTasks)
        fabAddTask = findViewById(R.id.favAddTask)

    }

    // Cuando se ejecuta en onClickListener en un item, entonces llama a esta funcion
    private fun onItemSelected(position: Int){
        // Si esta seleccionado, lo desele
        tasks[position].isSelected = !tasks[position].isSelected
       // Llamado al metodo para avisar al Adapter de que actualice la vista
        updateTasks()
    }

    private fun updateCategories(position: Int){
        categories[position].isSelected = !categories[position].isSelected
        categoriesAdapter.notifyItemChanged(position)
        updateTasks()
    }

    private fun updateTasks(){
        // Seleccioname las categorias que esten seleccionadas
        val selectedCategories: List<TaskCategory> = categories.filter { it.isSelected }
        val newTasks = tasks.filter { selectedCategories.contains(it.category) }
        tasksAdapter.tasks = newTasks

        // Esta funcion actualiza todos los datos, depende del caso,
        // debemos usar una funcion u otra
        tasksAdapter.notifyDataSetChanged()
    }


}