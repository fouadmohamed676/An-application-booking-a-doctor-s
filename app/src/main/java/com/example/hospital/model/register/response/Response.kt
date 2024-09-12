package com.example.hospital.model.register.response

data class Response(
    val barth_date: String,
    val email: String,
    val id: Int,
    val name: String,
    val password: String,
    val phone: String,
    val title: String
)