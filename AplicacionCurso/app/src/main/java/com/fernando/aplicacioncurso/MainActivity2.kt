package com.fernando.aplicacioncurso

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val texto = findViewById<TextView>(R.id.textViewSecundario)

        // Dame el extra, que puede estar vacio, si lo esta, se queda vacio
        val textoRecogido = intent.extras?.getString("Extra_Content").orEmpty()

        texto.text = textoRecogido


    }
}