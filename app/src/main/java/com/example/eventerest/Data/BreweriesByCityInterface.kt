package com.example.eventerest.Data

import com.example.eventerest.Data.BreweriesEntities.BreweriesByCityItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BreweriesByCityInterface {
    @GET("breweries?")
    fun getBreweriesByCity(@Query("by_city") cityName : String): Call<List<BreweriesByCityItem>>
}