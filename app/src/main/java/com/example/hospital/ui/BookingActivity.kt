package com.example.hospital.ui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hospital.R
import com.example.hospital.adapter.ServiceAdapter
import com.example.hospital.adapter.UserAdapter
import com.example.hospital.databinding.ActivityBookingBinding
import com.example.hospital.model.order.request.RequestOrder
import com.example.hospital.model.services.Response
import com.example.hospital.model.viewModel.ServiceViewModel
import com.example.hospital.utils.AuthData
import java.util.Calendar

class BookingActivity : AppCompatActivity(R.layout.activity_booking) {
    private lateinit var binding: ActivityBookingBinding
    private lateinit var viewModel: ServiceViewModel
    private var doctorId: Int = -1
    private var userId: Int = -1
    private lateinit var progressDialog: ProgressDialog
    private val servicesList = ArrayList<Response?>()
    private val userList = ArrayList<com.example.hospital.model.user.serviceById.Response?>()
    private val calendar: Calendar = Calendar.getInstance()
    private val year_: Int = calendar.get(Calendar.YEAR)
    private val month_: Int = calendar.get(Calendar.MONTH)
    private val day_: Int = calendar.get(Calendar.DAY_OF_MONTH)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(ServiceViewModel::class.java)
        progressDialog = ProgressDialog(this)
        getServices()
        binding.register.setOnClickListener {
            starProgress()
            sendOrder()
        }
    }


    private fun getServices() {
        servicesList.clear()
        viewModel.getAllServices.observe(this, Observer {
                response ->
            Log.e("response services : ", response.toString())
            servicesList.addAll(response)
            binding.spinnerServices.adapter = ServiceAdapter(this, servicesList!!)

            binding.spinnerServices.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    positoin: Int,
                    id: Long
                ) {
                    userId = servicesList[binding.spinnerServices.selectedItemPosition]!!.id
                    getUsers(userId)

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }

        })
        viewModel.getServices()
    }

    private fun getUsers(user_Id: Int) {
        viewModel.getServiceById.observe(this, Observer {
                response ->
            userList.clear()
            userList.addAll(response)
            binding.spinnerUsers.adapter = UserAdapter(this, userList)
            binding.spinnerUsers.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?, view: View?, position: Int, id: Long
                    ) {
                        doctorId = userList[binding.spinnerUsers.selectedItemPosition]!!.id
                     }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        Log.e("onNothingSelected","")
                    }
                }

        })
        viewModel.getServiceId(user_Id)
    }

    @SuppressLint("SuspiciousIndentation")
    private fun sendOrder() {
        viewModel.getResponseOrder.observe(this, Observer { response ->
            Log.e("response user : ", response.toString())
            AuthData.order_status = response[0]!!.status.name
            AuthData.order_date = response[0]!!.date
            val intent = Intent(this, SuccessActivity::class.java)
            startActivity(intent)
            stopProgress()
        })
        val  date =binding.regDate.text!!.trim().toString()
        val data = RequestOrder(doctorId.toString(),AuthData.patient_id,date)

        viewModel.submitOrder(data)
    }
    @SuppressLint("SetTextI18n")


    fun show(view: View) {
        val datePickerDialog = DatePickerDialog(
            this,
            {
                    view, year, month, day ->
                    binding.regDate.setText("$year-" + 1.plus(month).toString() + "-$day")
            }, year_, month_, day_
        )
        datePickerDialog.setTitle("تاريخ الحجز")
        datePickerDialog.show()
    }

    private fun starProgress() {
        progressDialog.setMessage("جاري حجز المعاينه")
        progressDialog.show()
        progressDialog.setCancelable(false)
    }

    private fun stopProgress() {
        progressDialog.dismiss()
    }
}