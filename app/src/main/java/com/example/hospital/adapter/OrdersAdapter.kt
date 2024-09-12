package com.example.hospital.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hospital.databinding.MyOrderItemBinding
import com.example.hospital.model.order.last.Response

class OrdersAdapter(private val listData:ArrayList<Response>) :
    RecyclerView.Adapter<OrdersAdapter.OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {

        return OrderViewHolder(
            MyOrderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        )
    }

    override fun getItemCount() = listData.size


    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)

    }

    class OrderViewHolder(private val binding: MyOrderItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Response) {
            binding.Tvdate.text =data.date
            binding.orderID.text=data.id.toString()
            binding.name.text=data.user.name
            binding.status.text=data.status.name
        }

    }
}