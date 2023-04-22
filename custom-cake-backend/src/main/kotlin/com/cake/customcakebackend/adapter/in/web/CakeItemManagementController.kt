package com.cake.customcakebackend.adapter.`in`.web

import com.cake.customcakebackend.adapter.`in`.web.dto.response.OperatorLoginResponse
import com.cake.customcakebackend.application.port.`in`.CakeItemManagementUseCase
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping(
    path = ["/operator"]
)
class CakeItemManagementController(
    private val cakeItemManagementUseCase: CakeItemManagementUseCase,

) {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/cake-item/{cakeItemId}")
    fun cakeItemInfo(
        httpServletRequest: HttpServletRequest,
        @SessionAttribute("operator") operatorLoginResponse: OperatorLoginResponse?,
        @PathVariable cakeItemId: Long,
        model: Model
    ): String {
        operatorLoginResponse
            ?: let {  // Session 에 Operator 정보가 없는 경우
                logger.info("케이크 상품 정보 로드 실패: 운영자 정보 없음")
                return "redirect:/operator/login"
            }
        // TODO
        return "cake-item-detail"
    }

    @GetMapping("/cake-item")
    fun cakeItemList(
        httpServletRequest: HttpServletRequest,
        @SessionAttribute("operator") operatorLoginResponse: OperatorLoginResponse?,
        model: Model
    ): String {
        operatorLoginResponse
            ?: let {  // Session 에 Operator 정보가 없는 경우
                logger.info("케이크 상품 리스트 로드 실패: 운영자 정보 없음")
                return "redirect:/operator/login"
            }

        operatorLoginResponse.storeId
            ?.let {
                val cakeItemResponseList = cakeItemManagementUseCase.loadCakeItemList(it)
                model.addAttribute("cakeItemResponseList", cakeItemResponseList)
            }

        // TODO check (operatorId.storeId == storeID)

        return "cake-item-management"
    }

    @GetMapping("/cake-item/form")
    fun addCakeItemForm(
        httpServletRequest: HttpServletRequest,
        @SessionAttribute("operator") operatorLoginResponse: OperatorLoginResponse?,
        model: Model
    ): String {
        operatorLoginResponse
            ?: let {  // Session 에 Operator 정보가 없는 경우
                logger.info("케이크 상품 추가 form 로드 실패: 운영자 정보 없음")
                return "redirect:/operator/login"
            }

        return "cake-item-add"
    }

    /**
     * deleteCakeItem method
     * : 케이크 옵션 삭제
     *
     * @author jjaen
     * @version 1.0.0
     * 작성일 2023/04/09
     **/
    @DeleteMapping("/cake-item/{cakeItemId}")
    fun deleteCakeItem(
        httpServletRequest: HttpServletRequest,
        @SessionAttribute("operator") operatorLoginResponse: OperatorLoginResponse?,
        @PathVariable cakeItemId: Long,
        redirectAttributes: RedirectAttributes
    ): String {
        operatorLoginResponse
            ?: let {  // Session 에 Operator 정보가 없는 경우
                logger.info("케이크 상품 삭제 실패: 운영자 정보 없음")
                return "redirect:/operator/login"
            }
        cakeItemManagementUseCase.deleteCakeItem(cakeItemId)

        return "redirect:/operator/cake-item"
    }
}