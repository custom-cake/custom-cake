package com.cake.customcakebackend.ui.admin.dto

data class SmsConfirmRequest(
        val phoneNumber: String,
        val code: String
)