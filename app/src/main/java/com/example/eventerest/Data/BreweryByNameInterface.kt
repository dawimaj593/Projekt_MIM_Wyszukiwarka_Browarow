package com.example.eventerest.Data

import com.example.eventerest.Data.BreweriesEntities.BreweriesByCityItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BreweryByNameInterface {
    @GET("breweries?")
    fun getBreweriesByName(@Query("by_name") cityName : String): Call<List<BreweriesByCityItem>>
}