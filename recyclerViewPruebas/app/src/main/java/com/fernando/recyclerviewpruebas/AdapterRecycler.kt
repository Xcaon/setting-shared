package com.fernando.recyclerviewpruebas

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterRecycler(var listadoCoches: MutableList<Coches>) : RecyclerView.Adapter<AdapterRecycler.CochesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CochesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CochesViewHolder(layoutInflater.inflate(R.layout.activity_coche, parent, false))
    }

    override fun onBindViewHolder(holder: CochesViewHolder, position: Int) {
        val item = listadoCoches[position]
        holder.renderCoches(item)

    }

    inner class CochesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvNombre = view.findViewById<TextView>(/* id = */ R.id.tvNombre)
        val btnEliminar = view.findViewById<Button>(R.id.btSeleccionar)

        fun renderCoches(coche: Coches){
            tvNombre.text = coche.nombreCoche
        }

    }

    override fun getItemCount(): Int {

        return listadoCoches.size
    }




}