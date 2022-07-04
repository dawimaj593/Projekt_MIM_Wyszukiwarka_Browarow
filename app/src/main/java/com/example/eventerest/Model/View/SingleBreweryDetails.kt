package com.example.eventerest.Model.View

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.eventerest.Model.SingleBrewery
import com.example.eventerest.R

class SingleBreweryDetails : Fragment() {

    lateinit var breweryName : TextView
    lateinit var breweryCityAndState : TextView
    lateinit var breweryType : TextView
    lateinit var breweryAddress : TextView
    lateinit var breweryPhone : TextView
    lateinit var breweryHttp : TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view : View =  inflater.inflate(R.layout.fragment_single_brewery_details, container, false)

        breweryName = view.findViewById(R.id.brewery_name_details_textview)
        breweryCityAndState = view.findViewById(R.id.brewery_city_and_state_details_textview)
        breweryType = view.findViewById(R.id.brewery_type_details_textview)
        breweryAddress = view.findViewById(R.id.brewery_address_details_textview)
        breweryPhone = view.findViewById(R.id.brewery_phone_details_textview)
        breweryHttp = view.findViewById(R.id.brewery_http_details_textview)

        breweryName.text = "name : " + SingleBrewery.name
        breweryCityAndState.text = "city :" + SingleBrewery.city + ", " + SingleBrewery.state
        breweryType.text = "type : " + SingleBrewery.type
        breweryAddress.text = "street : " + SingleBrewery.street
        breweryPhone.text = "phone : " + SingleBrewery.phone
        breweryHttp.text = "http : "  + SingleBrewery.http

        if (!SingleBrewery.http.equals("-")){
            breweryHttp.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setData(Uri.parse(SingleBrewery.http))
                startActivity(intent)
            }
        }

        return view
    }
}