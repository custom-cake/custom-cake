package com.cake.customcakebackend.adapter.`in`.web

import com.cake.customcakebackend.application.port.`in`.StoreManagementUseCase
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

@Controller
@RequestMapping("/store")
class StoreController(
    private val storeManagementUseCase: StoreManagementUseCase
) {

    @GetMapping("/{operatorId}")
    fun storeInfo(@Valid @PathVariable operatorId: Long, model: Model): String {
        val storeInfo = storeManagementUseCase.storeInfo(operatorId)
//        model.addAttribute("storeInfo", storeInfo)
        return "register"
    }
}