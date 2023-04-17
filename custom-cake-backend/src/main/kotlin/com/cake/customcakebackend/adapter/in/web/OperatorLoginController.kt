package com.cake.customcakebackend.adapter.`in`.web

import com.cake.customcakebackend.adapter.`in`.web.dto.request.OperatorLoginRequest
import com.cake.customcakebackend.adapter.`in`.web.dto.response.OperatorLoginResponse
import com.cake.customcakebackend.application.port.`in`.OperatorLoginUseCase
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

@Controller
@RequestMapping("/operator")
class OperatorLoginController(
    private val operatorLoginUseCase: OperatorLoginUseCase
) {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/login")
    fun operatorLoginForm(
        model: Model
    ): String {
        model.addAttribute("operatorLoginRequest", OperatorLoginRequest())
        return "login-form"
    }

    /**
     * operatorLogin method
     * : 운영자 세션 정보 유지를 위한 간단한 로그인 메소드
     *
     * @author jjaen
     * @version 1.0.0
     * 작성일 2023/04/17
    **/
    @PostMapping("/login")
    fun operatorLogin(
        httpServletRequest: HttpServletRequest,
        @ModelAttribute("operatorLoginRequest") @Validated operatorLoginRequest: OperatorLoginRequest,
        redirectAttributes: RedirectAttributes,
        result: BindingResult
    ): String {
        /**
         * @SessionAttribute
         * : controller method가 생성가는 모델 정보 중에서 지정한 이름과 동일한 이름이 있다면 session에 저장한다.
         *
         */
        if (result.hasErrors()) return "redirect:/operator/login"

        val operator = operatorLoginUseCase.login(operatorLoginRequest)
        val session = httpServletRequest.session

        return operator
            ?. let {// 로그인 성공
                session.setAttribute("operatorLoginResponse", operator)
                logger.info(operator.toString())
                "redirect:/"
            }
            ?: let {// 로그인 실패
                "redirect:/operator/login"
            }
    }

    @GetMapping("/logout")
    fun operatorLogout(
        httpServletRequest: HttpServletRequest,
        @SessionAttribute("operatorLoginResponse") operatorLoginResponse: OperatorLoginResponse?,  // TODO attribute name -> CONST
    ): String =
        operatorLoginResponse
            ?. let {
                // session 끊어주기
                httpServletRequest.session.invalidate()
                "redirect:/operator/login"
            }
            ?: let {  // Session 에 Operator 정보가 없는 경우
                logger.info("로그아웃 실패: 운영자 정보 없음")
                "redirect:/operator/login"
            }
}