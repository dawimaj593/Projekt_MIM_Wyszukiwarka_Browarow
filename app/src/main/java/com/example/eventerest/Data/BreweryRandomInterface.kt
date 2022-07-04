package com.example.eventerest.Data

import com.example.eventerest.Data.BreweriesEntities.BreweriesByCityItem
import retrofit2.Call
import retrofit2.http.GET

interface BreweryRandomInterface {
    @GET("breweries/random")
    fun getRandomBrewery() : Call<List<BreweriesByCityItem>>
}