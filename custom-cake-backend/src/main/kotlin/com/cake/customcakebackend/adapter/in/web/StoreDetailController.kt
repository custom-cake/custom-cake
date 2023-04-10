package com.cake.customcakebackend.adapter.`in`.web

import com.cake.customcakebackend.adapter.`in`.web.dto.response.StoreDetailInfoResponse
import com.cake.customcakebackend.application.port.`in`.StoreDetailUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/store")
class StoreDetailController(
   private val storeDetailUseCase: StoreDetailUseCase
) {

    @GetMapping("/{storeId}")
    fun getDetailInfo(
        @PathVariable storeId: Long
    ): StoreDetailInfoResponse =
        storeDetailUseCase.storeDetailInfo(storeId)
}