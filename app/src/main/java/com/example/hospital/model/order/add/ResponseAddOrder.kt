package com.example.hospital.model.order.add

data class ResponseAddOrder(
    val response: List<Response>,
    val service: Service,
    val status: String
)