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

    @GetMapping("/{operatorId}")
    fun storeInfo(@PathVariable operatorId: Long, model: Model): String {
        val storeList = storeManagementUseCase.storeInfo(operatorId)
        model.addAttribute("operatorId", operatorId)
        model.addAttribute("storeInfo", storeList.firstOrNull()?.toInfoResponse())
        return "store-management"
    }

    @GetMapping("/form")  // 매장 등록 버튼을 눌렀을 때
    fun registerStoreForm(@RequestParam operatorId: Long, model: Model): String {
        model.addAttribute("storeRegisterRequest", StoreRegisterRequest())
        model.addAttribute("dayOfDayValues", DayOfWeekUnit.values())
        return "store-register"
    }

    @PostMapping("")
    fun registerStore(@ModelAttribute storeRegisterRequest: StoreRegisterRequest, redirectAttributes: RedirectAttributes): String {
        storeManagementUseCase.registerStore(storeRegisterRequest)
        redirectAttributes.addAttribute("operatorId", storeRegisterRequest.operatorId)
        return "redirect:/operator/store/${storeRegisterRequest.operatorId}"
    }
}