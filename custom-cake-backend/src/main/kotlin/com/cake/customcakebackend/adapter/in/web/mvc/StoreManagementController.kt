package com.cake.customcakebackend.adapter.`in`.web.mvc

import com.cake.customcakebackend.adapter.`in`.web.dto.request.StoreRegisterRequest
import com.cake.customcakebackend.adapter.`in`.web.dto.response.OperatorLoginResponse
import com.cake.customcakebackend.adapter.`in`.web.dto.response.toInfoResponse
import com.cake.customcakebackend.application.port.`in`.StoreManagementUseCase
import com.cake.customcakebackend.common.DayOfWeekUnit
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping(
    path = ["/operator/store"],
)
class StoreManagementController(
    private val storeManagementUseCase: StoreManagementUseCase
) {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    /**
     * storeInfo method
     * : 매장 관리 페이지
     *
     * @author jjaen
     * @version 1.0.0
     * 작성일 2023/04/04
     **/
    @GetMapping("")
    fun storeInfo(
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
        model.addAttribute("dayOfWeekList", DayOfWeekUnit.toList())  // DOW 리스트
        // TODO 지정 휴무일 내려주고, 등록할 수 있도록
        return "store-management"
    }

    /**
     * registerStoreForm method
     * : 신규 매장 등록 버튼을 눌렀을 때 form page
     *
     * @author jjaen
     * @version 1.0.0
     * 작성일 2023/04/04
     **/
    @GetMapping("/form")
    fun registerStoreForm(
        httpServletRequest: HttpServletRequest,
        @SessionAttribute("operator") operatorLoginResponse: OperatorLoginResponse?,
        model: Model
    ): String {
        operatorLoginResponse
            ?: let {  // Session 에 Operator 정보가 없는 경우
                logger.info("매장 정보 로드 실패: 운영자 정보 없음")
                return "redirect:/operator/login"
            }
        model.addAttribute("storeRegisterRequest", StoreRegisterRequest())  // 필드 값 참조 위해 기본 값 객체 내려주기
        model.addAttribute("dayOfWeekList", DayOfWeekUnit.toList())  // DOW 리스트
        return "store-register"
    }

    /**
     * registerStore method
     * : 신규 매장 정보 입력 후, 저장 버튼을 눌렀을 때
     * - 매장 관리 페이지로 redirect
     *
     * @author jjaen
     * @version 1.0.0
     * 작성일 2023/04/04
    **/
    @PostMapping("")
    fun registerStore(
        httpServletRequest: HttpServletRequest,
        @SessionAttribute("operator") operatorLoginResponse: OperatorLoginResponse?,
        @Validated @ModelAttribute storeRegisterRequest: StoreRegisterRequest,
        result: BindingResult,
        redirectAttributes: RedirectAttributes
    ): String {
        if (result.hasErrors()) {
            return "store-register"
        }

        operatorLoginResponse
            ?: let {  // Session 에 Operator 정보가 없는 경우
                logger.info("매장 정보 로드 실패: 운영자 정보 없음")
                return "redirect:/operator/login"
            }

        // 매장 등록 check
        operatorLoginResponse.storeId
            ?. let {
                logger.info("매장 등록 실패: 매장이 이미 존재함")
            }
            ?: let{
                storeManagementUseCase.registerStore(operatorLoginResponse.id, storeRegisterRequest)
            }

        redirectAttributes.addAttribute("dayOfWeekList", DayOfWeekUnit.toList())  // 고정 휴무일 리스트
        return "redirect:/operator/store"
    }

    private fun addOperatorIdToModel(operatorId: Long, model: Model) =
        model.addAttribute("operatorId", operatorId)
}