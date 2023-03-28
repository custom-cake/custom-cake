package com.cake.customcakebackend.domain.usecase.signup

interface SendVerificationSmsUseCase {

    fun sendVerificationSms(phoneNumber: String)
}