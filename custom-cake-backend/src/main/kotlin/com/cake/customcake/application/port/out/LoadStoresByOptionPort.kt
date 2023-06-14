package com.cake.customcake.application.port.out

import com.cake.customcake.adapter.`in`.web.dto.request.StoreOptionSearchRequest
import com.cake.customcake.domain.Store

interface LoadStoresByOptionPort {
    fun loadByOption(request: StoreOptionSearchRequest): List<Store>
}