package com.cake.customcakebackend.ui.admin.api

import com.cake.customcakebackend.domain.usecase.signup.ConfirmVerificationCodeUseCase
import com.cake.customcakebackend.domain.usecase.signup.SendVerificationSmsUseCase
import com.cake.customcakebackend.ui.admin.dto.SmsConfirmRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/sign-up")
class SignUpApiController(
        private val sendVerificationSmsUseCase: SendVerificationSmsUseCase,
        private val confirmVerificationCodeUseCase: ConfirmVerificationCodeUseCase
) {

    @PostMapping("/sendSms")
    fun sendVerificationSms(@RequestParam("phoneNumber") phoneNumber: String) {
        sendVerificationSmsUseCase.sendVerificationSms(phoneNumber)
    }

    @PostMapping("/confirm")
    fun confirm(@RequestBody smsConfirmRequest: SmsConfirmRequest) : Boolean {
        return confirmVerificationCodeUseCase.confirm(
                phoneNumber = smsConfirmRequest.phoneNumber,
                code = smsConfirmRequest.code
        )
    }
}