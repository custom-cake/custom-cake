package com.cake.customcakebackend.adapter.`in`.web

import com.cake.customcakebackend.adapter.`in`.web.dto.request.StoreInfoRequest
import com.cake.customcakebackend.adapter.`in`.web.dto.response.StoreInfoResponse
import com.cake.customcakebackend.adapter.`in`.web.dto.response.toInfoResponse
import com.cake.customcakebackend.application.port.`in`.StoreManagementUseCase
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import javax.validation.Valid

@Controller
@RequestMapping("/operator/store")
class StoreController(
    private val storeManagementUseCase: StoreManagementUseCase
) {

    @GetMapping("/{operatorId}")
    fun storeInfo(@Valid @PathVariable operatorId: Long, model: Model): String {
        val storeInfo = storeManagementUseCase.storeInfo(operatorId)
//        model.addAttribute("storeInfo", storeInfo)
        return "index"
    }
}