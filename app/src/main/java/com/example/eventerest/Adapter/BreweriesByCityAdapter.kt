package com.example.eventerest.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.eventerest.Data.BreweriesEntities.BreweriesByCityItem
import com.example.eventerest.Model.SingleBrewery
import com.example.eventerest.R

class BreweriesByCityAdapter(var breweriesList: List<BreweriesByCityItem>, val view : View) : RecyclerView.Adapter<BreweriesByCityAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater : LayoutInflater = LayoutInflater.from(parent.context)
        val view : View = inflater.inflate(R.layout.brewery_row, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.cityName.text = breweriesList[position].city
        holder.breweryName.text = breweriesList[position].name

        holder.itemView.setOnClickListener {
            SingleBrewery.name = breweriesList[position].name
            SingleBrewery.city = breweriesList[position].city
            SingleBrewery.state = breweriesList[position].state
            if(breweriesList[position].street != null) SingleBrewery.street = breweriesList[position].street else SingleBrewery.street = "-"
            if(breweriesList[position].brewery_type != null) SingleBrewery.type = breweriesList[position].brewery_type else SingleBrewery.type = "-"
            if(breweriesList[position].phone != null) SingleBrewery.phone = breweriesList[position].phone else SingleBrewery.phone = "-"
            if(breweriesList[position].website_url != null) SingleBrewery.http = breweriesList[position].website_url else SingleBrewery.http = "-"

            Thread.sleep(1000)
            Navigation.findNavController(view).navigate(R.id.action_breweriesList_to_singleBreweryDetails)
        }
    }

    override fun getItemCount(): Int {
        return  breweriesList.size
    }

    inner class Holder(view : View) : RecyclerView.ViewHolder(view) {
        val cityName : TextView = view.findViewById(R.id.brewery_city_textview)
        val breweryName : TextView = view.findViewById(R.id.brewery_name_textview)
    }
}