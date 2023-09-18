package com.fernando.pruebaretrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
interface ApiService {
    @GET("/api/770089381555396/search/{name}")
    suspend fun getRespuestaApi(@Path("name") superHeroName: String): Response<DataResponse>
}