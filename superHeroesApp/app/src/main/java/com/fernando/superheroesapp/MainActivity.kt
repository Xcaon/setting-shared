package com.fernando.superheroesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.fernando.superheroesapp.DettailsSuperheroActivity.Companion.EXTRA_ID
import com.fernando.superheroesapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    // Configuramos el binding
    private lateinit var binding: ActivityMainBinding
    private lateinit var retrofit: Retrofit

    private lateinit var adapter: SuperheroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()
        initUI()
    }

    private fun initUI() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            // Un listener a la vez que vamos escribiendo, pero no vamos a usarlo en este momento
            // lo dejamos en false porque no podemos quitar la funcion
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        }
        )


        adapter = SuperheroAdapter { navigateToDetail(it) }
        binding.rvHeroes.setHasFixedSize(true)
        binding.rvHeroes.layoutManager = LinearLayoutManager(this)
        binding.rvHeroes.adapter = adapter

    }

    // Llamamos a esta funcion para conseguir los datos de la API
    private fun searchByName(query: String) {
        binding.progressBar.isVisible = true
        // Corrutinas ira en el hilo secundario
        CoroutineScope(Dispatchers.IO).launch {
            // Llamamos al api service
            val myResponse: Response<SuperHeroDataResponse> =
                retrofit.create(ApiService::class.java).getSuperheroes(query)
            if (myResponse.isSuccessful) {
                val response: SuperHeroDataResponse? = myResponse.body()
                if (response != null) {
                    Log.i("Fernando", response.toString())
                    // Las vistas se modifican en el hilo secundario por lo tanto debemos usar
                    runOnUiThread {
                        adapter.updatelist(response.superHeroes)
                        binding.progressBar.isVisible = false
                    }
                }
                Log.i("Fernando", "Funciona")
            } else {
                Log.i("Fernando", "No funciona")
            }
        }

    }

    private fun getRetrofit(): Retrofit {
        val retrofit = Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
    }


    private fun navigateToDetail(id: String) {
        val intent = Intent(this, DettailsSuperheroActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }

}