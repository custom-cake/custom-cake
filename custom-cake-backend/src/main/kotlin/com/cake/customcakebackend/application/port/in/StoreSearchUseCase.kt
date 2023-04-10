package com.cake.customcakebackend.application.port.`in`

import com.cake.customcakebackend.adapter.`in`.web.dto.response.StoreGetResponse

interface StoreSearchUseCase {
    fun searchByName(query: String): List<StoreGetResponse>
    fun getAllRegionsName(): List<String>
}