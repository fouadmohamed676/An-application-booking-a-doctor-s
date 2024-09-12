package com.example.hospital.model.order.add

data class Service(
    val created_at: String,
    val email: String,
    val email_verified_at: String,
    val gender_id: Int,
    val id: Int,
    val is_deleted: Int,
    val name: String,
    val phone: String,
    val service: ServiceX,
    val service_id: Int,
    val title: String,
    val updated_at: String
)