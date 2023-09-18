package com.fernando.pruebaretrofit

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.fernando.pruebaretrofit.databinding.ItemSuperheroBinding

class SuperHeroViewHolder (view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemSuperheroBinding.bind(view)
    fun bind(SuperHeroItemResponse: superHeroObjectResponse){
        binding.tvNombre.text = SuperHeroItemResponse.name
    }
}