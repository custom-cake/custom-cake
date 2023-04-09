package com.cake.customcakebackend.adapter.`in`.web.dto.request

import com.cake.customcakebackend.common.CakeOption1Type
import org.jetbrains.annotations.NotNull


data class CakeOption1AddRequest(
    @NotNull
    val cakeShape: CakeOption1Type.CakeShape? = null,
    @NotNull
    val cakeSize: CakeOption1Type.CakeSize? = null,
    @NotNull
    val cakeLayer: CakeOption1Type.CakeLayer? = null,
    val letteringLimit: Int = 0,
    val price: Int = 0
)
