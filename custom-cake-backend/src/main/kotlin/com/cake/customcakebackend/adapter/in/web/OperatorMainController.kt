package com.cake.customcakebackend.adapter.`in`.web

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class OperatorMainController {

    @GetMapping("/")
    fun main(model: Model): String {
        model.addAttribute("operatorId", 1)
        return "index"
    }

    @GetMapping("/buttons")
    fun buttons(): String {
        return "buttons"
    }

    @GetMapping("/cards")
    fun cards(): String {
        return "cards"
    }

    @GetMapping("/charts")
    fun charts(): String {
        return "charts"
    }

    @GetMapping("/tables")
    fun tables(): String {
        return "tables"
    }

    @GetMapping("/colors")
    fun colors(): String {
        return "utilities-color"
    }

    @GetMapping("/borders")
    fun borders(): String {
        return "utilities-border"
    }

    @GetMapping("/animations")
    fun animations(): String {
        return "utilities-animation"
    }

    @GetMapping("/other")
    fun other(): String {
        return "utilities-other"
    }
}