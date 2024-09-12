package com.example.hospital.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hospital.R
import com.example.hospital.adapter.OrdersAdapter
import com.example.hospital.databinding.ActivityOrdersBinding
import com.example.hospital.model.order.last.Response
import com.example.hospital.model.viewModel.OrderViewModel
import com.example.hospital.utils.AuthData.patient_id

class OrdersActivity : AppCompatActivity(R.layout.activity_orders) {
    private lateinit var binding: ActivityOrdersBinding
    private lateinit var viewModel: OrderViewModel
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private val orderList: ArrayList<Response> = ArrayList()
    private var patient_Id: Int = -1
    private lateinit var ordersAdapter: OrdersAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrdersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(OrderViewModel::class.java)
        setup()
        patient_Id = patient_id.toInt()
        getData()
    }

    private fun getData() {
        viewModel.getResponseOrders.observe(this, Observer { response ->
            orderList.addAll(response)
            if (orderList.isEmpty()) {
                binding.empData.visibility = View.VISIBLE
                binding.found.visibility = View.GONE
            } else {
                binding.found.visibility = View.VISIBLE
                binding.empData.visibility = View.GONE
            }
            ordersAdapter = OrdersAdapter(orderList)
            binding.orderRecycler.adapter = ordersAdapter
        })
        viewModel.getOrders(patient_Id)
    }

    private fun setup() {

        manager = LinearLayoutManager(this)
        layoutManager = LinearLayoutManager(this)
        binding.orderRecycler.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.orderRecycler.layoutManager = layoutManager
        binding.orderRecycler.setHasFixedSize(true)
    }

}