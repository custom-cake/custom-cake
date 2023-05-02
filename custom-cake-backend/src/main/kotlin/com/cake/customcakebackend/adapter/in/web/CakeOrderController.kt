package com.cake.customcakebackend.adapter.`in`.web

import com.cake.customcakebackend.adapter.`in`.web.dto.request.DesignCakeOrderRequest
import com.cake.customcakebackend.adapter.`in`.web.dto.response.CakeOrderListResponse
import com.cake.customcakebackend.application.port.`in`.DesignCakeOrderUseCase
import org.springframework.web.bind.annotation.*

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

    /**
     * orderList method
     * : 케이크 주문 내역 리스트
     * - /api/orders?userId=1
     *
     * @author jjaen
     * @version 1.0.0
     * 작성일 2023/05/02
    **/
    @GetMapping("")
    fun orderList(@RequestParam userId: Long): CakeOrderListResponse {
        return designCakeOrderUseCase.orderList(userId)
    }
}