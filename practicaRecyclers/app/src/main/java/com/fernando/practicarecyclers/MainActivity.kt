package com.fernando.practicarecyclers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val casas = listOf(
        Casa("Madrid", "vistas a la plaza del sol", 566),
        Casa("Barcelona", "Vistas al mar", 1650),
        Casa("Galicia", "Hacia las montañas", 1200)
    )

    private lateinit var rvCasas: RecyclerView
    private lateinit var casasAdapter: CasasAdapter
    private lateinit var edBuscador: EditText
    private lateinit var iv_lupa: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
        initUI()
        actualizarCasas()
    }

    private fun actualizarCasas() {
        iv_lupa.setOnClickListener {
            var valorBusqueda = edBuscador.text.toString()

            var nuevasCasas = buscarCasa(valorBusqueda)

            if (nuevasCasas != null) {
                casasAdapter.casas = nuevasCasas
                casasAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun buscarCasa(valorBusqueda: String): List<Casa> {
        var  nuevaLista : List<Casa> = emptyList()
        // Filtramos de las casas el que tenga el texto
        var nuevasCasas = casas.find { it.lugar == valorBusqueda }

        // Si hay alguno siendo no nulo
        // Entonces filtramos por la coincidencia para retornar la lista actualizada
        if (nuevasCasas != null) {
            nuevaLista = casas.filter {
                it.lugar == nuevasCasas.lugar
            }
        }

        return nuevaLista
    }

    private fun initComponents() {
        rvCasas = findViewById(R.id.rv_casas)
        edBuscador = findViewById(R.id.edBuscador)
        iv_lupa = findViewById(R.id.iv_lupa)
    }

    private fun initUI() {
        // Llamamos al adapter y le pasamos la lista de objetos
        casasAdapter = CasasAdapter(casas)
        rvCasas.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvCasas.adapter = casasAdapter
    }


}