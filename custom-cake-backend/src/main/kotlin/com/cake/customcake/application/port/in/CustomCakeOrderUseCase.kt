package com.cake.customcake.application.port.`in`

import com.cake.customcake.adapter.`in`.web.dto.response.CustomOrderOptionListResponse

interface CustomCakeOrderUseCase {
    fun loadAllCakeOptionList(storeId: Long): CustomOrderOptionListResponse
}