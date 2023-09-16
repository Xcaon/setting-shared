package com.fernando.practicarecyclers

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ViewHolderCasas(view: View) : RecyclerView.ViewHolder(view) {

    private val tvTitulo : TextView = view.findViewById(R.id.tv_lugarCasa)
    private val tv_vistas : TextView = view.findViewById(R.id.tv_vistas)
    private val tv_precio : TextView = view.findViewById(R.id.tv_precio)
    private val iv_fotocasa : ImageView = view.findViewById(R.id.iv_fotocasa)
    fun render(casa: Casa){
        tvTitulo.text = casa.lugar
        tv_vistas.text = casa.vistas
        tv_precio.text = casa.precio.toString() + " €"

    }

}