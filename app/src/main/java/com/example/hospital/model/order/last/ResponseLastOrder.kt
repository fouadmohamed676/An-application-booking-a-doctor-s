package com.example.hospital.model.order.last

data class ResponseLastOrder(
    val response: List<Response>,
    val status: String
)