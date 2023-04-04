package com.cake.customcakebackend.adapter.`in`.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class OperatorMainController {

    @GetMapping("/")
    fun main(): String {
        return "index"
    }
}