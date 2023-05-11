package com.cake.customcake.adapter.`in`.web.mvc

import com.cake.customcake.adapter.`in`.web.dto.request.CustomCakeSheetRequest
import com.cake.customcake.adapter.`in`.web.dto.response.OperatorLoginResponse
import com.cake.customcake.application.port.`in`.CakeOrderManagementUseCase
import com.cake.customcake.application.port.`in`.CustomCakeOrderUseCase
import com.cake.customcake.common.OrderStatus
import com.cake.customcake.common.OrderType
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping(
    path = ["/operator/order"]
)
class CakeOrderManagementController(
    private val cakeOrderManagementUseCase: CakeOrderManagementUseCase,
    private val customCakeOrderUseCase: CustomCakeOrderUseCase
) {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    /**
     * 주문 상태에 따른 주문 리스트 로드
     *
     * @author jjaen
     * @version 1.0.0
     * 작성일 2023/05/02
    **/
    @GetMapping("")
    fun cakeOrderList(
        httpServletRequest: HttpServletRequest,
        @SessionAttribute("operator") operatorLoginResponse: OperatorLoginResponse?,
        @RequestParam status: OrderStatus,
        model: Model
    ): String {
        operatorLoginResponse
            ?: let {
                logger.info("주문 관리 화면 로드 실패: 운영자 정보 없음")
                return "redirect:/operator/login"
            }

        operatorLoginResponse.storeId
            ?.let {
                val cakeOrderList = cakeOrderManagementUseCase.loadCakeOrderList(it, status)
                model.addAttribute("cakeOrderList", cakeOrderList)
            }
            ?: let{
                logger.info("주문 정보 로드 실패: 매장 정보 않음")
            }

        return when (status) {
            OrderStatus.NEW -> "order-management-new"
            OrderStatus.IN_PROGRESS -> "order-management-in-progress"
            OrderStatus.PICK_UP -> "order-management-pick-up"
            OrderStatus.CANCELED -> "order-management-canceled"
        }
    }

    /**
     * 신규 주문 승인
     *
     * @author jjaen
     * @version 1.0.0
     * 작성일 2023/05/04
    **/
    @PostMapping("/{orderId}")
    fun approveCakeOrder(
        httpServletRequest: HttpServletRequest,
        @SessionAttribute("operator") operatorLoginResponse: OperatorLoginResponse?,
        @PathVariable orderId: Long,
        @RequestParam type: OrderType,
        redirectAttributes: RedirectAttributes,
    ): String {
        operatorLoginResponse
            ?: let {
                logger.info("주문 승인 실패: 운영자 정보 없음")
                return "redirect:/operator/login"
            }

        operatorLoginResponse.storeId
            ?.let {
                cakeOrderManagementUseCase.approveCakeOrder(type, orderId)
            }
            ?: let{
                logger.info("주문 승인 실패: 매장 정보 없음")
            }

        return "redirect:/operator/order?status=NEW"
    }

    /**
     * 커스텀 케이크 주문서 승인
     *
     * @author jjaen
     * @version 1.0.0
     * 작성일 2023/05/09
     **/
    @PostMapping("/custom")
    fun approveCakeOrderSheet(
        httpServletRequest: HttpServletRequest,
        @SessionAttribute("operator") operatorLoginResponse: OperatorLoginResponse?,
        @RequestBody customCakeSheetRequest: CustomCakeSheetRequest,
        redirectAttributes: RedirectAttributes,
    ): String {
        operatorLoginResponse
            ?: let {
                logger.info("주문서 승인 실패: 운영자 정보 없음")
                return "redirect:/operator/login"
            }

        if (operatorLoginResponse.storeId == customCakeSheetRequest.storeId) {
            customCakeOrderUseCase.makeCustomCakeSheet(customCakeSheetRequest)
        } else {
            logger.info("주문서 승인 실패: 매장 정보 불일치")
            return "redirect:/operator/login"
        }
            
        return "redirect:/operator/chat?status=IN_PROGRESS"
    }
}