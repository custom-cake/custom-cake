package com.cake.customcakebackend.adapter.`in`.web

import com.cake.customcakebackend.adapter.`in`.web.dto.request.StoreRegisterRequest
import com.cake.customcakebackend.adapter.`in`.web.dto.response.toInfoResponse
import com.cake.customcakebackend.application.port.`in`.StoreManagementUseCase
import com.cake.customcakebackend.common.DayOfWeekUnit
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
@RequestMapping(
    path = ["/operator/store"],
)
class StoreManagementController(
    private val storeManagementUseCase: StoreManagementUseCase
) {

    /**
     * storeInfo method
     * : 매장 관리 페이지
     *
     * @author jjaen
     * @version 1.0.0
     * 작성일 2023/04/04
    **/
    @GetMapping("/{operatorId}")
    fun storeInfo(@PathVariable operatorId: Long, model: Model): String {
        val storeList = storeManagementUseCase.storeInfo(operatorId)
        model.addAttribute("operatorId", operatorId)
        model.addAttribute("storeInfo", storeList.firstOrNull()?.toInfoResponse())
        model.addAttribute("dayOfWeekList", DayOfWeekUnit.toList())  // DOW 리스트
        // TODO 지정 휴무일 내려주기
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
    fun registerStoreForm(@RequestParam operatorId: Long, model: Model): String {
        model.addAttribute("storeRegisterRequest", StoreRegisterRequest())  // 필드 값 참조 위해 기본 값 객체 내려주기
        model.addAttribute("dayOfWeekList", DayOfWeekUnit.toList())  // 고정 휴무일 리스트
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
    fun registerStore(@ModelAttribute storeRegisterRequest: StoreRegisterRequest, redirectAttributes: RedirectAttributes): String {
        storeManagementUseCase.registerStore(storeRegisterRequest)
        redirectAttributes.addAttribute("operatorId", storeRegisterRequest.operatorId)
        return "redirect:/operator/store/${storeRegisterRequest.operatorId}"
    }
}