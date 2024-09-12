package com.example.hospital.model.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospital.model.order.request.RequestOrder
import com.example.hospital.model.services.Response
import com.example.hospital.network.RetrofitInstance
import com.example.hospital.utils.AuthData.response_order_data
import kotlinx.coroutines.launch

class ServiceViewModel:ViewModel() {
    private val _responseServices: MutableLiveData<List<Response>> =
        MutableLiveData()
    val getAllServices: LiveData<List<Response>>
        get() = _responseServices
    private val serviceId: MutableLiveData<List<com.example.hospital.model.user.serviceById.Response?>> =
        MutableLiveData()
    val getServiceById: LiveData<List<com.example.hospital.model.user.serviceById.Response?>>
        get() = serviceId
        private val userId: MutableLiveData<List<com.example.hospital.model.user.serviceById.Response?>> =
        MutableLiveData()
    val getUserById: LiveData<List<com.example.hospital.model.user.serviceById.Response?>>
        get() = userId
      private val responseOrder: MutableLiveData<List<com.example.hospital.model.order.add.Response?>> =
        MutableLiveData()
    val getResponseOrder: LiveData<List<com.example.hospital.model.order.add.Response?>>
        get() = responseOrder

    fun getServices(){
        viewModelScope.launch {
            val data =RetrofitInstance.api.getServices()
            if (data.isSuccessful){
                _responseServices.value=data.body()!!.response
            }
            else{
                _responseServices.value=null
            }
        }
    }
    fun getServiceId(service_id : Int){
        viewModelScope.launch {
            val data =RetrofitInstance.api.getServiceUser(service_id)
            if (data.isSuccessful){
                Log.e("response VM : ",data.body()!!.response.toString())
                    serviceId.value=data.body()!!.response
            }
            else{
                serviceId.value=null
            }
        }
    }
    fun getUserId(user_id : Int){
        viewModelScope.launch {
            val data =RetrofitInstance.api.getServiceUser(user_id)
            if (data.isSuccessful){
                serviceId.value=data.body()!!.response
            }
            else{
                _responseServices.value=null
            }
        }
    }
    fun submitOrder(order: RequestOrder  ){

        Log.e("Request : ", order.toString())
        viewModelScope.launch {
            val data =RetrofitInstance.api.order(order)
            if (data.isSuccessful){
                response_order_data.addAll(data.body()!!.response)
                Log.e("response : " ,data.body().toString())
                responseOrder.value=data.body()!!.response
            }
            else{
                responseOrder.value=null
            }
        }
    }


}