package com.example.hospital.model.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospital.model.order.last.Response
import com.example.hospital.network.RetrofitInstance
import kotlinx.coroutines.launch

class OrderViewModel : ViewModel() {

    private val _responseOrders: MutableLiveData<List<Response>> =
        MutableLiveData()
    val getResponseOrders: LiveData<List<Response>>
        get() = _responseOrders

    fun getOrders(patient_id: Int) {
        viewModelScope.launch {
            val data = RetrofitInstance.api.getOrders(patient_id)
            if (data.isSuccessful){
                try {

                    _responseOrders.value=data.body()!!.response
                    Log.e("response VM: ",data.body()!!.response.toString())


                }catch (e:Exception){

                    _responseOrders.value=null
                    Log.e("Message : ",e.message.toString())

                }
            }
            else{

                Log.e("Error : ",data.errorBody().toString())
            }
        }

    }

}