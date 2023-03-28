package com.cake.customcakebackend.domain.usecase.signup

interface ConfirmVerificationCodeUseCase {

    fun confirm(phoneNumber: String, code: String): Boolean
}
