package com.example.hospital.model.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospital.model.login.request.LoginRequest
import com.example.hospital.model.register.RegisterModel
import com.example.hospital.model.register.response.Response
import com.example.hospital.network.RetrofitInstance
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val patient: MutableLiveData<Response> = MutableLiveData()
    val patientData: LiveData<Response>
        get() = patient
    private val _responseLogin: MutableLiveData<List<com.example.hospital.model.login.data.Response>> =
        MutableLiveData()
    val getResponseLogin: LiveData<List<com.example.hospital.model.login.data.Response>>
        get() = _responseLogin

    fun loginUser(data: LoginRequest) {

        viewModelScope.launch {

            val response = RetrofitInstance.api.login(data)

            try {
                if (response.isSuccessful) {

                    if (response.body()!!.status == "success") {
                        _responseLogin.value = response.body()!!.response
                    } else if (response.body()!!.status == "fail") {
                        _responseLogin.value = arrayListOf()
                        Log.e("Login Fail : ", response.code().toString())
                    }
                }

            } catch (e: Exception) {
                _responseLogin.value = null
                Log.e("message : ", e.message.toString())

            }

        }

    }

    fun register(data: RegisterModel) {

        viewModelScope.launch {

            try {
                val data = RetrofitInstance.api.register(data)
                if (data.isSuccessful) {
                    patient.value = data.body()?.response
                } else {

                    Log.e("try Again : ", data.message().toString())

                }

            } catch (e: Exception) {

                Log.e("message : ", e.message.toString())
            }


        }
    }
//    fun order(data: RegisterModel) {
//
//        viewModelScope.launch {
//
//            try {
//                val data = RetrofitInstance.api.order(data)
//                if (data.isSuccessful) {
//                    patient.value = data.body()?.response
//                } else {
//
//                    Log.e("try Again : ", data.message().toString())
//
//                }
//
//            } catch (e: Exception) {
//
//                Log.e("message : ", e.message.toString())
//            }
//
//
//        }
//    }


}