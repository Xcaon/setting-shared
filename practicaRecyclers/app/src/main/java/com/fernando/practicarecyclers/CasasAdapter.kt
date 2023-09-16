package com.fernando.practicarecyclers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CasasAdapter ( var casas: List<Casa> ) : RecyclerView.Adapter<ViewHolderCasas>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCasas {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_casa, parent, false)
        return ViewHolderCasas(view)
    }

    override fun onBindViewHolder(holder: ViewHolderCasas, position: Int) {
        holder.render(casas[position])
    }

    override fun getItemCount(): Int {
        return casas.size
    }


}