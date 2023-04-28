package com.cake.customcakebackend.adapter.`in`.web

import com.cake.customcakebackend.adapter.`in`.web.dto.request.DesignCakeOrderRequest
import com.cake.customcakebackend.application.port.`in`.DesignCakeOrderUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(
    path = ["/api/orders"]
)
class CakeOrderController(
    private val designCakeOrderUseCase: DesignCakeOrderUseCase
) {

    @PostMapping("/designs")
    fun orderDesignCake(@RequestBody designCakeOrderRequest: DesignCakeOrderRequest) {
        designCakeOrderUseCase.order(designCakeOrderRequest)
    }
}