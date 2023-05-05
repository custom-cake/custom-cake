package com.cake.customcake.adapter.`in`.web.dto.request

import com.cake.customcake.domain.CakeOption

abstract class CakeOptionRequest {
    abstract fun toDomain(storeId: Long): CakeOption
}