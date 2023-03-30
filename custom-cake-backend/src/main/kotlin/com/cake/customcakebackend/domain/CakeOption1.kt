package com.cake.customcakebackend.domain

import com.cake.customcakebackend.common.CakeOption1Type
import java.time.LocalDateTime

data class CakeOption1(
    val id: Long,
    val storeId: Long,
    val cakeShape: CakeOption1Type.CakeSpace,  // ENUM(CIRCLE,SQUARE,HEART)
    val cakeSize: CakeOption1Type.CakeSize,  // ENUM(NO_1, ... ,NO_6)
    val letteringLimit: Int,
    val cakeLayer: CakeOption1Type.CakeLayer,  //  ENUM(LAYER_1,LAYER_2,LAYER_3)
    val price: Int,
    val isUsed: Boolean,
    val isDeleted: Boolean,
    val createdAt: LocalDateTime,
    var modifiedAt: LocalDateTime
)
