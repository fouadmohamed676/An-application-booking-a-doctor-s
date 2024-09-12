package com.example.hospital.model.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospital.model.banners.Response
import com.example.hospital.network.RetrofitInstance
import kotlinx.coroutines.launch

class BannersViewModel : ViewModel() {
    private val _responseBanners: MutableLiveData<List<Response>> = MutableLiveData()
    val getResponseBanners: LiveData<List<Response>>
        get() = _responseBanners

    fun getBannersFun() {
        viewModelScope.launch {
            val data = RetrofitInstance.api.getBanners()
            if (data.isSuccessful) {
                try {
                    _responseBanners.value = data.body()!!.response
                } catch (e: Exception) {
                    _responseBanners.value = null
                    Log.e("Message : ", e.message.toString())
                }
            } else {
                Log.e("Error : ", data.errorBody().toString())
            }
        }

    }


}