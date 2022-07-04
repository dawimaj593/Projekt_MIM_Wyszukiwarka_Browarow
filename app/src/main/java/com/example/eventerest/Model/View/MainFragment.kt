package com.example.eventerest.Model.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.example.eventerest.Data.BreweriesEntities.BreweriesByCityItem
import com.example.eventerest.Data.BreweriesByCityInterface
import com.example.eventerest.Data.BreweryByNameInterface
import com.example.eventerest.Data.BreweryRandomInterface
import com.example.eventerest.Model.ListOfBreweries
import com.example.eventerest.Model.SingleBrewery
import com.example.eventerest.R
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class MainFragment : Fragment() {

    lateinit var breweryByCity : Button
    lateinit var breweryByName : Button
    lateinit var breweryRandom : Button
    lateinit var searchbreveryInputText : TextInputEditText
    var httpLink : String = "https://api.openbrewerydb.org/"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view : View = inflater.inflate(R.layout.fragment_main, container, false)

        breweryByCity = view.findViewById(R.id.search_by_city_button)
        breweryRandom = view.findViewById(R.id.random_brewery_button)
        breweryByName = view.findViewById(R.id.search_by_name_button)
        searchbreveryInputText = view.findViewById(R.id.input_breweries_edittext)

        breweryByCity.setOnClickListener {
            getBreveriesByCity(searchbreveryInputText.text.toString())
            Thread.sleep(1000)
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_breweriesList)
        }
        breweryRandom.setOnClickListener {
            getRandomBrewery()
            Thread.sleep(1000)
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_singleBreweryDetails)
        }
        breweryByName.setOnClickListener {
            getBreveriesByName(searchbreveryInputText.text.toString())
            Thread.sleep(1000)
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_breweriesList)
        }

        return view
    }

    private fun getRandomBrewery() {
        val retrofitBilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(httpLink)
            .build()
            .create(BreweryRandomInterface::class.java)

        val retrofitData = retrofitBilder.getRandomBrewery()

        retrofitData.enqueue(object : Callback<List<BreweriesByCityItem>?> {
            override fun onResponse(call: Call<List<BreweriesByCityItem>?>, response: Response<List<BreweriesByCityItem>?>) {
                try {
                    val responseBodyItem = response.body()?.get(0)
                    SingleBrewery.name = responseBodyItem!!.name
                    SingleBrewery.city = responseBodyItem.city
                    SingleBrewery.street = responseBodyItem.street
                    SingleBrewery.state = responseBodyItem.state
                    SingleBrewery.phone = responseBodyItem.phone
                    SingleBrewery.type = responseBodyItem.brewery_type
                    SingleBrewery.http = responseBodyItem.website_url

                } catch (e : Exception){}
            }

            override fun onFailure(call: Call<List<BreweriesByCityItem>?>, t: Throwable) {
                Log.d("MainActivity", "Failure " + t.message)
            }
        })
    }
    private fun getBreveriesByCity(name : String) {
        val retrofitBilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(httpLink)
            .build()
            .create(BreweriesByCityInterface::class.java)

        val retrofitData = retrofitBilder.getBreweriesByCity(name)

        retrofitData.enqueue(object : Callback<List<BreweriesByCityItem>?> {
            override fun onResponse(call: Call<List<BreweriesByCityItem>?>, response: Response<List<BreweriesByCityItem>?>) {
                try {
                    val responseBody = response.body()
                    val list = mutableListOf<BreweriesByCityItem>()
                    for (item in responseBody!!){
                        list.add(item)
                    }
                    ListOfBreweries.listOfBreweries = list
                    Log.d("MainActivity", ListOfBreweries.listOfBreweries.toString())
                } catch (e : Exception){ }
            }
            override fun onFailure(call: Call<List<BreweriesByCityItem>?>, t: Throwable) {
                Log.d("MainActivity", "Failure " + t.message)
            }
        })
    }
    private fun getBreveriesByName(name : String) {
        val retrofitBilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(httpLink)
            .build()
            .create(BreweryByNameInterface::class.java)

        val retrofitData = retrofitBilder.getBreweriesByName(name)

        retrofitData.enqueue(object : Callback<List<BreweriesByCityItem>?> {
            override fun onResponse(call: Call<List<BreweriesByCityItem>?>, response: Response<List<BreweriesByCityItem>?>) {
                try {
                    val responseBody = response.body()
                    val list = mutableListOf<BreweriesByCityItem>()
                    for (item in responseBody!!){
                        list.add(item)
                    }
                    ListOfBreweries.listOfBreweries = list
                    Log.d("MainActivity", ListOfBreweries.listOfBreweries.toString())
                } catch (e : Exception){ }
            }

            override fun onFailure(call: Call<List<BreweriesByCityItem>?>, t: Throwable) {
                Log.d("MainActivity", "Failure " + t.message)
            }
        })
    }

}


