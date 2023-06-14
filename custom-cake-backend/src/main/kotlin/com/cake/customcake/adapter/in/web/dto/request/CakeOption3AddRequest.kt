package com.cake.customcake.adapter.`in`.web.dto.request

import com.cake.customcake.domain.CakeOption
import com.cake.customcake.domain.CakeOption3
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime


data class CakeOption3AddRequest(
    @NotNull
    val name: String? = null,
    val price: Int = 0
) : CakeOptionRequest() {
    override fun toDomain(storeId: Long): CakeOption =
        CakeOption3(
            storeId = storeId,
            name = this.name!!,
            price = this.price,
            isUsed = true,
            isDeleted = false,
            createdAt = LocalDateTime.now(),
            modifiedAt = LocalDateTime.now()
        )
}
