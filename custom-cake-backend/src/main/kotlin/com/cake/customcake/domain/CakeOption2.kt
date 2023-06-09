package com.cake.customcake.domain

import com.cake.customcake.common.CakeOption2Type
import java.time.LocalDateTime

data class CakeOption2(
    val id: Long = 0,
    val storeId: Long,
    val cakeSheet: CakeOption2Type.CakeSheet,  // ENUM(CHOCO, BANILA)
    val cakeInnerCream: CakeOption2Type.CakeInnerCream,  // ENUM(CREAMCHEESE, CHOCO,...)
    val cakeOuterCream: CakeOption2Type.CakeOuterCream,  // ENUM(CREAMCHEESE, CHOCO,...)
    val price: Int,
    val isUsed: Boolean,
    val isDeleted: Boolean,
    val createdAt: LocalDateTime,
    var modifiedAt: LocalDateTime
) : CakeOption() {
    override fun toResponse(): OptionResponse =  OptionResponse(
        id = this.id,
        type = 2,
        value = "${cakeSheet.type}, ${cakeInnerCream.type}, ${cakeOuterCream.type}",
        price = this.price
    )
}