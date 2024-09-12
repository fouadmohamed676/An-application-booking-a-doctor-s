package com.example.hospital.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.hospital.R
import com.example.hospital.model.services.Response

class ServiceAdapter(context: Context, val list: ArrayList<Response?>) :
ArrayAdapter<Response>(context, 0, list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return myView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return myView(position, convertView, parent)
    }

    private fun myView(position: Int, convertView: View?, parent: ViewGroup): View {

        val list = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.drop_item, parent, false
        )

        list.let {
            val name = view.findViewById<TextView>(R.id.name_)
            name.text = list?.name!!
        }

        return view
    }


}