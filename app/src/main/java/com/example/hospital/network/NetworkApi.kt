package com.example.hospital.network

import com.example.hospital.model.banners.ResponseBanners
import com.example.hospital.model.login.data.ResponseLoginData
import com.example.hospital.model.login.request.LoginRequest
import com.example.hospital.model.order.add.ResponseAddOrder
import com.example.hospital.model.order.last.ResponseLastOrder
import com.example.hospital.model.order.request.RequestOrder
import com.example.hospital.model.register.RegisterModel
import com.example.hospital.model.register.response.RegisterResponse
import com.example.hospital.model.services.ServiceResponse
import com.example.hospital.model.user.serviceById.ResponseServiceId
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface NetworkApi {

    @GET("services")        // get All Services
    suspend fun getServices(): Response<ServiceResponse>
    @GET("banners")        // get All Banners
    suspend fun getBanners(): Response<ResponseBanners>
    @GET("service/{id}")    // all doctors in this service
    suspend fun getServiceUser(@Path("id") service_id: Int): Response<ResponseServiceId>
    @GET("order/last/{id}")    // all Patient Orders
    suspend fun getOrders(@Path("id") patient_id: Int): Response<ResponseLastOrder>
    @POST("patient/register")   // register Patient
    suspend fun register(@Body data: RegisterModel): Response<RegisterResponse>
    @POST("patient/login")  // login
    suspend fun login(@Body data: LoginRequest): Response<ResponseLoginData>
    @POST("order/store")    // save order
    suspend fun order(@Body data: RequestOrder): Response<ResponseAddOrder>


}