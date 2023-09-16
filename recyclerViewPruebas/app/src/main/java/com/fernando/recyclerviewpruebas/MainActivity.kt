package com.fernando.recyclerviewpruebas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ejecutarRecyclerView()
    }

    private fun ejecutarRecyclerView() {

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerCoche)

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = AdapterRecycler(listadoCoches.listadoCoches)

    }
}