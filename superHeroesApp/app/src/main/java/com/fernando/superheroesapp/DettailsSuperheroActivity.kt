package com.fernando.superheroesapp

import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import com.fernando.superheroesapp.databinding.ActivityDettailsSuperheroBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

class DettailsSuperheroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDettailsSuperheroBinding
    companion object {
        const val EXTRA_ID = "extra_id"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDettailsSuperheroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Comprobar si es vacio, si es vacio lo deja vacio, para evitar errores
        val id = intent.getStringExtra(EXTRA_ID).orEmpty()
        getSuperheroInformation(id)

    }

    private fun getRetrofit(): Retrofit {
        val retrofit = Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
    }

    private fun getSuperheroInformation(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val superheroDetail = getRetrofit().create(ApiService::class.java).getSuperheroDetail(id)

            if (superheroDetail.body() != null){
                runOnUiThread {
                    createUI(superheroDetail.body()!!)
                }

            }
        }
    }

    // Aqui nos llega todos los elementos del superheroe y lo pintamos
    private fun createUI(superhero: SuperHeroDetailResponse) {
        Picasso.get().load(superhero.image.url).into(binding.ivSuperhero)
        binding.tvSuperName.text = superhero.name
        prepareStats(superhero.powerstats)
    }

    private fun prepareStats(powerstats: PowerStatsResponse) {

        updateHeight(binding.viewCombat, powerstats.combat)
        updateHeight(binding.viewDurability, powerstats.durability)
        updateHeight(binding.viewIntelligence, powerstats.intelligence)
        updateHeight(binding.viewSpeed, powerstats.speed)
        updateHeight(binding.viewStrength, powerstats.strength)
        updateHeight(binding.viewPower, powerstats.power)
    }

    private fun updateHeight(view : View, stat: String){
        val params = view.layoutParams
        params.height = pixelToDp(stat.toFloat())
        view.layoutParams = params
    }

    private fun pixelToDp(px: Float) : Int{
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics).roundToInt()
    }
}