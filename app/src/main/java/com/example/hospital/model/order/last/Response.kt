package com.example.hospital.model.order.last

data class Response(
    val date: String,
    val id: Int,
    val status: Status,
    val status_id: Int,
    val user: User
)