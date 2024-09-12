package com.example.hospital.model.login.response

data class Response(
    val barth_date: String,
    val created_at: String,
    val email: String,
    val id: Int,
    val is_deleted: Int,
    val name: String,
    val password: String,
    val phone: String,
    val title: String,
    val updated_at: String
)