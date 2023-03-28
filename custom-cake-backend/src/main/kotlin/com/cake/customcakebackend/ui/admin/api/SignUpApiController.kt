package com.cake.customcakebackend.ui.admin.api

import com.cake.customcakebackend.domain.usecase.signup.SendVerificationSmsUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/sign-up")
class SignUpApiController(
        private val sendVerificationSmsUseCase: SendVerificationSmsUseCase
) {

    @PostMapping("/sendSms")
    fun sendVerificationSms(@RequestParam("phoneNumber") phoneNumber: String) {
        sendVerificationSmsUseCase.sendVerificationSms(phoneNumber)
    }
}