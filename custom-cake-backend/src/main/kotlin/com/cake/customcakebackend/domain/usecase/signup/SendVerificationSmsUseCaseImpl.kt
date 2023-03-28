package com.cake.customcakebackend.domain.usecase.signup

import com.cake.customcakebackend.infrastructure.gateway.SmsGateway
import com.cake.customcakebackend.infrastructure.gateway.VerificationCodeStorage
import org.springframework.stereotype.Component

@Component
class SendVerificationSmsUseCaseImpl(
        private val smsGateway: SmsGateway,
        private val verificationCodeStorage: VerificationCodeStorage
) : SendVerificationSmsUseCase {

    override fun sendVerificationSms(phoneNumber: String) {
        val code = generateVerificationCode()
        verificationCodeStorage.put(phoneNumber, code)
        smsGateway.send(phoneNumber, code)
    }

    // Todo : verification code 정책 확립 및 리펙토링
    private fun generateVerificationCode(): String {
        val random = java.util.Random()
        return String.format("%06d", random.nextInt(1000000))
    }
}