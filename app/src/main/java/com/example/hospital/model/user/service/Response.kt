package com.example.hospital.model.user.service

data class Response(
    val created_at: String,
    val email: String,
    val email_verified_at: String,
    val gender_id: Int,
    val id: Int,
    val is_deleted: Int,
    val name: String,
    val phone: String,
    val service: Service,
    val service_id: Int,
    val title: String,
    val updated_at: String
)