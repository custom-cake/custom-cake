package com.cake.customcakebackend.adapter.`in`.web

import com.cake.customcakebackend.adapter.`in`.web.dto.request.StoreRegisterRequest
import com.cake.customcakebackend.application.port.`in`.StoreManagementUseCase
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

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
        model.addAttribute("storeInfo", storeList.firstOrNull())
        return "store-management"
    }

    @GetMapping("/form")  // 매장 등록 버튼을 눌렀을 때
    fun registerStoreForm(model: Model): String {
        model.addAttribute("storeRegisterRequest", StoreRegisterRequest())
        return "store-register"
    }

    @PostMapping("")
    fun registerStore(@RequestBody storeRegisterRequest: StoreRegisterRequest): String {
        storeManagementUseCase.registerStore()
        return "redirect:/operator/store/${storeRegisterRequest.operatorId}"
    }
}