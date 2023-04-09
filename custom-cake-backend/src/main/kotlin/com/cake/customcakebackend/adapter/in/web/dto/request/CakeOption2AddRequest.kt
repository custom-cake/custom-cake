package com.cake.customcakebackend.adapter.`in`.web.dto.request

import com.cake.customcakebackend.common.CakeOption2Type
import org.jetbrains.annotations.NotNull


data class CakeOption2AddRequest(
    @NotNull
    val cakeSheet: CakeOption2Type.CakeSheet? = null,
    @NotNull
    val cakeInnerCream: CakeOption2Type.CakeOuterCream? = null,
    @NotNull
    val cakeOuterCream: CakeOption2Type.CakeOuterCream? = null,
    val price: Int = 0
)
