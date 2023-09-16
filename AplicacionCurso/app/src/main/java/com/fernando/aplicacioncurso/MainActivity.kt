package com.fernando.aplicacioncurso

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val texto = findViewById<TextView>(R.id.titulo)
        val textoIntroducido = findViewById<EditText>(R.id.editTextText)
        val boton = findViewById<Button>(R.id.button)

        val intent = Intent(this, MainActivity2::class.java)



        boton.setOnClickListener {
            val textoPasar = textoIntroducido.text.toString()
            intent.putExtra("Extra_Content", textoPasar)
            startActivity(intent)
        }
    }
}