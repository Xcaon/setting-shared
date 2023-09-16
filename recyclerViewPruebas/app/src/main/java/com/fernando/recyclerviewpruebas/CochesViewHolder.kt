package com.fernando.recyclerviewpruebas

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class CochesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val tvNombre = view.findViewById<TextView>(R.id.tvNombre)
    val btSeleccionar = view.findViewById<Button>(R.id.btSeleccionar)

    fun renderCoches(coche: Coches){
        tvNombre.text = coche.nombreCoche

    }

    fun showPosition(position: Int, listadoCoches: MutableList<Coches>) {
        btSeleccionar.setOnClickListener {
            Log.i("fernandoLag", "Esta es la posicion: $position")
            listadoCoches.removeAt(position)
        }

    }

    init {
        itemView.setOnClickListener {
            Toast.makeText(view.context, "Test", Toast.LENGTH_LONG).show()
        }


    }

}
