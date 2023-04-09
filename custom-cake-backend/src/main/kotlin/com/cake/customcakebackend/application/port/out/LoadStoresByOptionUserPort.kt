package com.cake.customcakebackend.application.port.out

import com.cake.customcakebackend.adapter.`in`.web.dto.request.StoreOptionSearchRequest
import com.cake.customcakebackend.domain.Store

interface LoadStoresByOptionUserPort {
    fun loadByOption(request: StoreOptionSearchRequest): List<Store>
}