package com.cake.customcakebackend.adapter.`in`.web

import com.cake.customcakebackend.adapter.`in`.web.dto.response.OperatorLoginResponse
import com.cake.customcakebackend.common.ChatStatus
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
    path = ["/operator"]
)
class CakeChatManagementController {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/chat")
    fun chatPages(
        httpServletRequest: HttpServletRequest,
        @SessionAttribute("operator") operatorLoginResponse: OperatorLoginResponse?,
        @RequestParam status: ChatStatus,
        model: Model
    ): String {
        operatorLoginResponse
            ?: let {  // Session 에 Operator 정보가 없는 경우
                logger.info("채팅 화면 로드 실패: 운영자 정보 없음")
                return "redirect:/operator/login"
            }

        return when (status) {
            ChatStatus.NEW -> "chat-management-new"
            ChatStatus.IN_PROGRESS -> "chat-management-in-progress"
            ChatStatus.COMPLETED -> "chat-management-completed"
            else -> "404"
        }
    }

}