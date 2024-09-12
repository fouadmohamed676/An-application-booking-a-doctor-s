package com.example.hospital.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hospital.R
import com.example.hospital.adapter.BannersAdapter
import com.example.hospital.adapter.OrdersAdapter
import com.example.hospital.databinding.ActivityHomeBinding
import com.example.hospital.model.banners.Response
import com.example.hospital.model.viewModel.BannersViewModel
import com.example.hospital.utils.AuthData

class HomeActivity :AppCompatActivity(R.layout.activity_home) {
    private lateinit var binding: ActivityHomeBinding
    private  lateinit var viewModel: BannersViewModel
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var bannersAdapter: BannersAdapter//: BannersAdapter
    private val orderList: ArrayList<Response> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel=ViewModelProvider(this).get(BannersViewModel::class.java)
        binding.name.text=AuthData.patient_name
        binding.interView.setOnClickListener {
            val intent = Intent(this, BookingActivity::class.java)
            startActivity(intent)
        }
        binding.lastBooking.setOnClickListener {
            val intent = Intent(this, OrdersActivity::class.java)
            startActivity(intent)
        }
        binding.info.setOnClickListener {
            val intent = Intent(this, InfoActivity::class.java)
            startActivity(intent)
        }
        binding.about.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
        setup()
        getBannersData()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getBannersData() {
        viewModel.getResponseBanners.observe(this, Observer {
            response->
            orderList.addAll(response)
            bannersAdapter = BannersAdapter(orderList)
            binding.dayrec.adapter = bannersAdapter
            bannersAdapter.notifyDataSetChanged()
        })
        viewModel.getBannersFun()
    }

    fun logout(view: View) {
        AuthData.patient_name=""
        AuthData.patient_email=""
        AuthData.response_order_data.clear()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private var doubleBackToExitPressedOnce: Boolean = false
    @Suppress("OVERRIDE_DEPRECATION")
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            finish()
            finishAffinity()
            super.finish()
            return
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "إضغط مره اخرى للخروج", Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            doubleBackToExitPressedOnce = false
        }, 2000)
    }

    private fun setup() {
        layoutManager = LinearLayoutManager(this)
        binding.dayrec.setHasFixedSize(true)
        binding.dayrec.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
    }


}