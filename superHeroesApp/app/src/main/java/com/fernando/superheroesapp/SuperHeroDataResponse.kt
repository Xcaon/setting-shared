package com.fernando.superheroesapp

import com.google.gson.annotations.SerializedName

// Debe llamarse igual que en el JSON, si queremos llamar a la variable diferente
// Debemos usar el @serializedName
// Buenas practicas para obfuscar el código
data class SuperHeroDataResponse(
    @SerializedName("response") val response: String,
    @SerializedName("results") val superHeroes: List<SuperHeroItemResponse>
)

data class SuperHeroItemResponse(
    @SerializedName("id") val superheroId: String,
    @SerializedName("name") val superheroName: String,

    )