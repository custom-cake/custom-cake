package com.cake.customcake.domain

import com.cake.customcake.common.CakeOption1Type
import java.time.LocalDateTime

data class CakeOption1(
    val id: Long = 0,
    val storeId: Long,
    val cakeShape: CakeOption1Type.CakeShape,  // ENUM(CIRCLE,SQUARE,HEART)
    val cakeSize: CakeOption1Type.CakeSize,  // ENUM(NO_1, ... ,NO_6)
    val letteringLimit: Int,
    val cakeLayer: CakeOption1Type.CakeLayer,  //  ENUM(LAYER_1,LAYER_2,LAYER_3)
    val price: Int,
    val isUsed: Boolean,
    val isDeleted: Boolean,
    val createdAt: LocalDateTime,
    var modifiedAt: LocalDateTime
) : CakeOption() {
    override fun toResponse(): OptionResponse =  OptionResponse(
        id = this.id,
        type = 1,
        value = "${cakeShape.type}, ${cakeSize.type}, ${cakeLayer.type}, 레터링 ${letteringLimit}자 제한",
        price = this.price
    )
}
