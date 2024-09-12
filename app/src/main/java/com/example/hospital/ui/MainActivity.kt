@file:Suppress("DEPRECATION")

package com.example.hospital.ui

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hospital.R
import com.example.hospital.databinding.ActivityMainBinding
import com.example.hospital.model.login.request.LoginRequest
import com.example.hospital.model.viewModel.UserViewModel
import com.example.hospital.utils.AuthData
import com.example.hospital.utils.AuthData.patient_email
import com.example.hospital.utils.AuthData.patient_id
import com.example.hospital.utils.AuthData.patient_name
import com.example.hospital.utils.AuthData.patient_phone

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var viewModel: UserViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        progressDialog = ProgressDialog(this)

        binding.login.setOnClickListener {
            if (binding.email.text.isNullOrEmpty()) {
                Toast.makeText(this, "الحقل مطلوب", Toast.LENGTH_SHORT).show()
            } else {
                starProgress()
                login()
            }
        }
        binding.register.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }


    private fun login() {

        val email = binding.email.text.toString().trim()
        val data = LoginRequest(email)
        viewModel.getResponseLogin.observe(this, Observer { response ->
            try {
            stopProgress()
            if (response.isNullOrEmpty()) {
                Toast.makeText(this, "تأكد من اسم المستخدم", Toast.LENGTH_SHORT).show()

                patient_email=""
            } else {
                    patient_id = response[0].id
                    patient_name = response[0].name
                    patient_email = response[0].email
                    patient_phone = response[0].phone
                    activity()
                }
            }
                catch (e: Exception) {
                    Log.e("login message : ", e.toString())
                }

        })
        viewModel.loginUser(data)
    }

    private fun activity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }


    private fun starProgress() {
        progressDialog.setMessage("جاري تسجيل الدخول.. ")
        progressDialog.show()
        progressDialog.setCancelable(false)
    }

    private fun stopProgress() {
        progressDialog.dismiss()
    }

    private var doubleBackToExitPressedOnce: Boolean = false

    @Suppress("OVERRIDE_DEPRECATION")
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            patient_name = ""
            patient_email = ""
            AuthData.response_order_data.clear()
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