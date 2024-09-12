package com.example.hospital.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hospital.R
import com.example.hospital.databinding.SuccessActivityBinding
import com.example.hospital.utils.AuthData.order_date
import com.example.hospital.utils.AuthData.response_order_data

class SuccessActivity : AppCompatActivity(R.layout.success_activity) {

    private lateinit var binding: SuccessActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SuccessActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.date.text = order_date
        binding.email.text = response_order_data[0]!!.user.email
        binding.doctorName.text = response_order_data[0]!!.user.name
        binding.status.text = response_order_data[0]!!.status.name

        binding.submit.setOnClickListener {
            activity()
        }
    }

    private fun activity() {
        val intent = Intent(this, HomeActivity::class.java)
        response_order_data.clear()
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
}