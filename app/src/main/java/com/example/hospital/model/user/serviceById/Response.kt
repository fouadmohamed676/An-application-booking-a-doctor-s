package com.example.hospital.model.user.serviceById

data class Response(
    val created_at: String,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val service: Service,
    val service_id: Int,
    val title: String,
    val updated_at: String
)