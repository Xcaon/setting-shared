package com.fernando.pruebaretrofit

import com.google.gson.annotations.SerializedName

data class DataResponse (
    @SerializedName("response") val response: String,
    @SerializedName("results") val superHeroes: List<superHeroObjectResponse>
)

data class superHeroObjectResponse (
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String
)

