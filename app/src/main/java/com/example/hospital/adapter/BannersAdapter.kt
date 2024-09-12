package com.example.hospital.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hospital.databinding.BannerItemBinding
import com.example.hospital.model.banners.Response

class BannersAdapter (private val listData:ArrayList<Response>) :
    RecyclerView.Adapter<BannersAdapter.OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {

        return OrderViewHolder(
            BannerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        )
    }

    override fun getItemCount() = listData.size


    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)

    }

    class OrderViewHolder(private val binding: BannerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Response) {
            binding.name.text = data.title
            binding.date.text = data.date
        }

    }
}