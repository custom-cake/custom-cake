package com.cake.customcake.application.port.`in`

import com.cake.customcake.adapter.`in`.web.dto.request.CustomCakeOrderRequest
import com.cake.customcake.adapter.`in`.web.dto.request.CustomCakeSheetRequest
import com.cake.customcake.adapter.`in`.web.dto.response.CustomOrderOptionListResponse

interface CustomCakeOrderUseCase {
    fun loadAllCakeOptionList(storeId: Long): CustomOrderOptionListResponse
    fun makeCustomCakeSheet(customCakeSheetRequest: CustomCakeSheetRequest)
    fun orderCustomCake(customCakeSheetRequest: CustomCakeOrderRequest)
}