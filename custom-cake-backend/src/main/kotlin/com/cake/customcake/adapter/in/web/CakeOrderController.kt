package com.cake.customcake.adapter.`in`.web

import com.cake.customcake.adapter.`in`.web.dto.request.CustomCakeOrderRequest
import com.cake.customcake.adapter.`in`.web.dto.request.DesignCakeOrderRequest
import com.cake.customcake.adapter.`in`.web.dto.response.CakeOrderListResponse
import com.cake.customcake.adapter.`in`.web.dto.response.CustomOrderOptionListResponse
import com.cake.customcake.application.port.`in`.CustomCakeOrderUseCase
import com.cake.customcake.application.port.`in`.DesignCakeOrderUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
    path = ["/api/orders"]
)
class CakeOrderController(
    private val designCakeOrderUseCase: DesignCakeOrderUseCase,
    private val customCakeOrderUseCase: CustomCakeOrderUseCase
) {

    @PostMapping("/designs")
    fun orderDesignCake(@RequestBody designCakeOrderRequest: DesignCakeOrderRequest) {
        designCakeOrderUseCase.order(designCakeOrderRequest)
    }

    /**
     * 케이크 주문 내역 리스트
     * - /api/orders?userId=1
     *
     * @author jjaen
     * @version 1.0.0
     * 작성일 2023/05/02
    **/
    @GetMapping("")
    fun orderList(@RequestParam userId: Long): CakeOrderListResponse =
        designCakeOrderUseCase.orderList(userId)


    /**
     * 커스텀 케이크 주문 시, 옵션 리스트
     *
     * @author jjaen
     * @version 1.0.0
     * 작성일 2023/05/09
    **/
    @GetMapping("/customs/options")
    fun customCakeOptionList(@RequestParam storeId: Long): CustomOrderOptionListResponse =
        customCakeOrderUseCase.loadAllCakeOptionList(storeId)

    /**
     * 커스텀 케이크 주문 생성
     * - 사용자가 결제 버튼을 클릭한 경우
     *
     * @author jjaen
     * @version 1.0.0
     * 작성일 2023/05/09
    **/
    @PostMapping("/customs")
    fun orderCustomCake(@RequestBody customCakeOrderRequest: CustomCakeOrderRequest) {
        customCakeOrderUseCase.orderCustomCake(customCakeOrderRequest)
    }
}