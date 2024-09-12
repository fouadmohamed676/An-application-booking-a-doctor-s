package com.example.hospital.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hospital.R
import com.example.hospital.model.order.last.Response

class MyOrdersAdapter (private val blogListSubServices: ArrayList<Response>) :
    RecyclerView.Adapter<MyOrdersAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.my_order_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = blogListSubServices[position]
        return holder.bindData(data)
    }

    override fun getItemCount() = blogListSubServices.size

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(data: Response) {
            itemView.findViewById<TextView>(R.id.date).text=data.user.name
        }
    }
}