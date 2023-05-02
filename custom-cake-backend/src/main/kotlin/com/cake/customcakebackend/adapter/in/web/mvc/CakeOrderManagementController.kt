package com.cake.customcakebackend.adapter.`in`.web.mvc

import com.cake.customcakebackend.adapter.`in`.web.dto.response.OperatorLoginResponse
import com.cake.customcakebackend.application.port.`in`.CakeOrderManagementUseCase
import com.cake.customcakebackend.common.OrderStatus
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.SessionAttribute
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping(
    path = ["/operator/order"]
)
class CakeOrderManagementController(
    private val cakeOrderManagementUseCase: CakeOrderManagementUseCase
) {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    /**
     * cakeOrderList method
     * : 주문 상태에 따른 주문 리스트 로드
     *
     * @author jjaen
     * @version 1.0.0
     * 작성일 2023/05/02
    **/
    @GetMapping("")
    fun cakeOrderList(
        httpServletRequest: HttpServletRequest,
        @SessionAttribute("operator") operatorLoginResponse: OperatorLoginResponse?,
        @RequestParam orderStatus: OrderStatus,
        model: Model
    ): String {
        operatorLoginResponse
            ?: let {
                logger.info("주문 관리 화면 로드 실패: 운영자 정보 없음")
                return "redirect:/operator/login"
            }

        operatorLoginResponse.storeId
            ?.let {
                val cakeOrderList = cakeOrderManagementUseCase.loadCakeOrderList(it, orderStatus)
                model.addAttribute("cakeOrderList", cakeOrderList)
            }
            ?: let{
                logger.info("주문 정보 로드 실패: 매장 정보 않음")
            }

        return when (orderStatus) {
            OrderStatus.NEW -> "order-management-new"
            OrderStatus.IN_PROGRESS -> "order-management-in-progress"
            OrderStatus.PICK_UP -> "order-management-pick-up"
            OrderStatus.CANCELED -> "order-management-canceled"
        }
    }


}