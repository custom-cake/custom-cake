package com.cake.customcake.adapter.`in`.web.dto.response

import com.cake.customcake.common.CakeOption1Type
import com.cake.customcake.common.CakeOption2Type

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