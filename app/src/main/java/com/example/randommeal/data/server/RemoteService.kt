package com.example.randommeal.data.server

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RemoteService {

    @GET("recipes/complexSearch")
    suspend fun listRamdomMeals(
        @Query("query") query: String,
        @Query("number") number: String,
        @Query("apiKey") apiKey: String,
    ): RemoteMealsResult

    @GET("/recipes/{id}/information")
        suspend fun getRecipeById(
        @Path("id") searchById:String,
        @Query("apiKey") apiKey: String,
        ): Result

}