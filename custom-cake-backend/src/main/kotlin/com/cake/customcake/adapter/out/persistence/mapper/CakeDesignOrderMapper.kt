package com.cake.customcake.adapter.out.persistence.mapper

import com.cake.customcake.adapter.out.persistence.entity.CakeDesignOrderEntity
import com.cake.customcake.domain.CakeDesignOrder
import org.springframework.stereotype.Component

@Component
class CakeDesignOrderMapper : Mapper<CakeDesignOrderEntity, CakeDesignOrder> {

    override fun toEntity(domain: CakeDesignOrder): CakeDesignOrderEntity =
        CakeDesignOrderEntity(
            id = domain.id,
            userId = domain.userId,
            storeId = domain.storeId,
            cakeItemId = domain.cakeItemId,
            optionByCakeIdList = domain.optionByCakeIdList,
            requirements = domain.requirements,
            orderStatus = domain.orderStatus,
            paymentAmount = domain.paymentAmount,
            purchaseConfirmationDate = domain.purchaseConfirmationDate,
            pickupDatetime = domain.pickupDatetime
        )

    override fun toDomain(entity: CakeDesignOrderEntity): CakeDesignOrder =
        CakeDesignOrder(
            id = entity.id,
            userId = entity.userId,
            storeId = entity.storeId,
            cakeItemId = entity.cakeItemId,
            optionByCakeIdList = entity.optionByCakeIdList,
            requirements = entity.requirements,
            orderStatus = entity.orderStatus,
            paymentAmount = entity.paymentAmount,
            pickupDatetime = entity.pickupDatetime,
            purchaseConfirmationDate = entity.purchaseConfirmationDate,
            createdAt = entity.createdAt,
            modifiedAt = entity.modifiedAt
        )
}