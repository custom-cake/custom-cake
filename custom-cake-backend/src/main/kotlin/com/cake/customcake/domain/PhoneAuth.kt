package com.cake.customcake.domain

data class PhoneAuth (
    val id: Long,
    val phone: String,
    val authToken: String
)