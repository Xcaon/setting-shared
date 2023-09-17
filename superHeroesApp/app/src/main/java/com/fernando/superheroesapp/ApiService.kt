package com.fernando.superheroesapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    // Usar RetroFit y corrutinas
    // Al final va el valor que vamos sustituyendo segun lo que busquemos
    // El endpoint que vamos a usar segun lo que queramos que nos devuelva
    @GET("/api/770089381555396/search/{name}")
    suspend fun getSuperheroes(@Path("name") superheroName:String): Response<SuperHeroDataResponse>

    @GET("/api/770089381555396/{id}")
    suspend fun getSuperheroDetail(@Path("id") superheroId:String):Response<SuperHeroDetailResponse>

}