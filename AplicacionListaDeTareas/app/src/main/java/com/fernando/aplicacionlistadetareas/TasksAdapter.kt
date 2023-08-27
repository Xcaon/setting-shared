package com.fernando.aplicacionlistadetareas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

// onTaskSelected nos permite devolver el index del elemento seleccionado y trabajar con el
class TasksAdapter( var tasks: List<Task>, private val onTaskSeleced: (Int) -> Unit) :
    RecyclerView.Adapter<TasksViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_todo_task, parent, false)
        return TasksViewHolder(view)
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        // Rellenamos los items con lo que le digamos en el ViewHolder
        holder.render(tasks[position])

        // cada item tendra un listener
        holder.itemView.setOnClickListener {
            // Llamamos a la funcion landa pasandole la posicion
            onTaskSeleced(position)
        }

    }

    override fun getItemCount(): Int {
        return tasks.size
    }


}