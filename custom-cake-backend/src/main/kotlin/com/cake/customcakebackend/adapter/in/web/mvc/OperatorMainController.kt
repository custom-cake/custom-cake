package com.cake.customcakebackend.adapter.`in`.web.mvc

import com.cake.customcakebackend.adapter.`in`.web.dto.response.OperatorLoginResponse
import com.cake.customcakebackend.adapter.`in`.web.dto.response.toInfoResponse
import com.cake.customcakebackend.application.port.`in`.StoreManagementUseCase
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.SessionAttribute
import javax.servlet.http.HttpServletRequest

@Controller
class OperatorMainController(
    private val storeManagementUseCase: StoreManagementUseCase
) {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/")
    fun main(
        httpServletRequest: HttpServletRequest,
        @SessionAttribute("operator") operatorLoginResponse: OperatorLoginResponse?,
        model: Model
    ): String {
        operatorLoginResponse
            ?: let {  // Session 에 Operator 정보가 없는 경우
                logger.info("매장 정보 로드 실패: 운영자 정보 없음")
                return "redirect:/operator/login"
            }
        val storeList = storeManagementUseCase.storeInfo(operatorLoginResponse.id)

        model.addAttribute("storeInfo", storeList.firstOrNull()?.toInfoResponse())
        return "dashboard"
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

    @GetMapping("/`animations`")
    fun animations(): String {
        return "utilities-animation"
    }

    @GetMapping("/other")
    fun other(): String {
        return "utilities-other"
    }
}