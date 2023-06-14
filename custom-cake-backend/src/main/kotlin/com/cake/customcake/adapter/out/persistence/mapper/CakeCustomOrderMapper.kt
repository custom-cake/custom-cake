package com.cake.customcake.adapter.out.persistence.mapper

import com.cake.customcake.adapter.out.persistence.entity.CakeCustomOrderEntity
import com.cake.customcake.adapter.out.persistence.entity.CakeDesignOrderEntity
import com.cake.customcake.domain.CakeCustomOrder
import com.cake.customcake.domain.CakeDesignOrder
import org.springframework.stereotype.Component

@Component
class CakeCustomOrderMapper(
    private val cakeCustomOrderSheetMapper: CakeCustomOrderSheetMapper
) : Mapper<CakeCustomOrderEntity, CakeCustomOrder> {

    override fun toEntity(domain: CakeCustomOrder): CakeCustomOrderEntity =
        CakeCustomOrderEntity(
            id = domain.id,
            userId = domain.userId,
            storeId = domain.storeId,
            cakeCustomOrderSheet = cakeCustomOrderSheetMapper.toEntity(domain.cakeCustomOrderSheet),
            orderStatus = domain.orderStatus,
            paymentAmount = domain.paymentAmount,
            purchaseConfirmationDate = domain.purchaseConfirmationDate,
            pickupDatetime = domain.pickupDatetime
        )

    override fun toDomain(entity: CakeCustomOrderEntity): CakeCustomOrder =
        CakeCustomOrder(
            id = entity.id,
            userId = entity.userId,
            storeId = entity.storeId,
            cakeCustomOrderSheet = cakeCustomOrderSheetMapper.toDomain(entity.cakeCustomOrderSheet),
            orderStatus = entity.orderStatus,
            paymentAmount = entity.paymentAmount,
            pickupDatetime = entity.pickupDatetime,
            purchaseConfirmationDate = entity.purchaseConfirmationDate,
            createdAt = entity.createdAt,
            modifiedAt = entity.modifiedAt
        )
}