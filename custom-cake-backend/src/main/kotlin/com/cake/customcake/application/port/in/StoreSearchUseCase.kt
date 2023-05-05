package com.cake.customcake.application.port.`in`

import com.cake.customcake.adapter.`in`.web.dto.request.StoreOptionSearchRequest
import com.cake.customcake.adapter.`in`.web.dto.response.StoreGetResponse

interface StoreSearchUseCase {
    fun searchByName(query: String): List<StoreGetResponse>
    fun getAllRegionsName(): List<String>
    fun searchByOption(request: StoreOptionSearchRequest): List<StoreGetResponse>
}