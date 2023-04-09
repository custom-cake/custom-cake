package com.cake.customcakebackend.adapter.`in`.web.dto.request

import com.cake.customcakebackend.common.CakeOption2Type
import com.cake.customcakebackend.domain.CakeOption
import com.cake.customcakebackend.domain.CakeOption2
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime


data class CakeOption2AddRequest(
    @NotNull
    val cakeSheet: CakeOption2Type.CakeSheet? = null,
    @NotNull
    val cakeInnerCream: CakeOption2Type.CakeInnerCream? = null,
    @NotNull
    val cakeOuterCream: CakeOption2Type.CakeOuterCream? = null,
    val price: Int = 0
) : CakeOptionRequest() {
    override fun toDomain(storeId: Long): CakeOption =
        CakeOption2(
            storeId = storeId,
            cakeSheet = this.cakeSheet!!,
            cakeInnerCream = this.cakeInnerCream!!,
            cakeOuterCream = this.cakeOuterCream!!,
            price = this.price,
            isUsed = true,
            isDeleted = false,
            createdAt = LocalDateTime.now(),
            modifiedAt = LocalDateTime.now()
        )
}
