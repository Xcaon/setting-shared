package com.fernando.superheroesapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.fernando.superheroesapp.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperheroViewHolder (view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemSuperheroBinding.bind(view)
    fun bind(SuperHeroItemResponse : SuperHeroItemResponse, onItemSelected: (String) -> Unit){
        binding.tvSuperheroName.text = SuperHeroItemResponse.superheroName
        Picasso.get().load(SuperHeroItemResponse.superheroImage.url).into(binding.ivSuperhero)
        // root es toda la vista
        // Esto es un listerner hecho con las funciones lamba
        binding.root.setOnClickListener { onItemSelected(SuperHeroItemResponse.superheroId) }
    }
}