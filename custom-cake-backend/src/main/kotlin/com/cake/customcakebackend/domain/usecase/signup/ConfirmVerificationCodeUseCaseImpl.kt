package com.cake.customcakebackend.domain.usecase.signup

import com.cake.customcakebackend.infrastructure.gateway.VerificationCodeStorage
import org.springframework.stereotype.Component

@Component
class ConfirmVerificationCodeUseCaseImpl(
        private val verificationCodeStorage: VerificationCodeStorage
) : ConfirmVerificationCodeUseCase {

    override fun confirm(phoneNumber: String, code: String): Boolean {
        val result = verificationCodeStorage.confirm(phoneNumber, code)

        // do something

        return result
    }
}