package com.cake.customcakebackend.adapter.`in`.web.dto.request

import com.cake.customcakebackend.common.CakeOption1Type
import com.cake.customcakebackend.domain.CakeOption
import com.cake.customcakebackend.domain.CakeOption1
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime


data class CakeOption1AddRequest(
    @NotNull
    val cakeShape: CakeOption1Type.CakeShape? = null,
    @NotNull
    val cakeSize: CakeOption1Type.CakeSize? = null,
    @NotNull
    val cakeLayer: CakeOption1Type.CakeLayer? = null,
    val letteringLimit: Int = 0,
    val price: Int = 0
) : CakeOptionRequest() {
    override fun toDomain(storeId: Long): CakeOption =
        CakeOption1(
            storeId = storeId,
            cakeShape = this.cakeShape!!,
            cakeSize = this.cakeSize!!,
            cakeLayer = this.cakeLayer!!,
            letteringLimit = this.letteringLimit,
            price = this.price,
            isUsed = true,
            isDeleted = false,
            createdAt = LocalDateTime.now(),
            modifiedAt = LocalDateTime.now()
        )
}
