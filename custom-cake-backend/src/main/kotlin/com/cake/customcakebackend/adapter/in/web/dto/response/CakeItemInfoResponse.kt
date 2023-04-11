package com.cake.customcakebackend.adapter.`in`.web.dto.response

import com.cake.customcakebackend.common.CakeOption1Type
import com.cake.customcakebackend.common.CakeOption2Type

data class CakeItemInfoResponse(
    val cakeShape: CakeOption1Type.CakeShape,
    val cakeSize: CakeOption1Type.CakeSize,
    val cakeLayer: CakeOption1Type.CakeLayer,
    val letteringLimit: Int = 0,
    val cakeSheet: CakeOption2Type.CakeSheet,
    val cakeInnerCream: CakeOption2Type.CakeInnerCream,
    val cakeOuterCream: CakeOption2Type.CakeOuterCream,
    val name: String,
    val price: Int = 0,
)