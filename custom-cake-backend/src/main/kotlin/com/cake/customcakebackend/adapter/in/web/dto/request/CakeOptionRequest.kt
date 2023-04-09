package com.cake.customcakebackend.adapter.`in`.web.dto.request

import com.cake.customcakebackend.domain.CakeOption

abstract class CakeOptionRequest {
    abstract fun toDomain(storeId: Long): CakeOption
}