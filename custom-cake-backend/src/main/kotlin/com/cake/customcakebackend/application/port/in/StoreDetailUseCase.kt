package com.cake.customcakebackend.application.port.`in`

import com.cake.customcakebackend.adapter.`in`.web.dto.response.StoreDetailInfoResponse

interface StoreDetailUseCase {

    fun storeDetailInfo(storeId: Long): StoreDetailInfoResponse
}