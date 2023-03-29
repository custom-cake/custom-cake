package com.cake.customcakebackend.adapter.`in`.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/operator")
class OperatorLoginController {

    @GetMapping("/login")
    fun login(): String {
        return "login"
    }
}