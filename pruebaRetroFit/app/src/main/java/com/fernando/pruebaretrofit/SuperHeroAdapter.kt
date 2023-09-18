package com.fernando.pruebaretrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SuperHeroAdapter(var superheroList: List<superHeroObjectResponse> = emptyList()) :
    RecyclerView.Adapter<SuperHeroViewHolder>() {

    fun updatelist(list: List<superHeroObjectResponse>){
        superheroList = list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SuperHeroViewHolder(layoutInflater.inflate(R.layout.item_superhero, parent, false))
    }

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        val item = superheroList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return superheroList.size
    }


}