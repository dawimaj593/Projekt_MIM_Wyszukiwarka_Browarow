package com.example.eventerest.Model.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eventerest.Adapter.BreweriesByCityAdapter
import com.example.eventerest.Model.ListOfBreweries
import com.example.eventerest.R

class BreweriesList : Fragment() {

    lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view : View = inflater.inflate(R.layout.fragment_breweries_list, container, false)

        recyclerView = view.findViewById(R.id.breweries_list_recyclerview)
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        recyclerView.adapter = BreweriesByCityAdapter(ListOfBreweries.listOfBreweries, view)

        return view
    }
}