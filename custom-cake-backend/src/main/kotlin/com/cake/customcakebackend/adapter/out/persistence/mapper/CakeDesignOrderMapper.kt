package com.cake.customcakebackend.adapter.out.persistence.mapper

import com.cake.customcakebackend.adapter.out.persistence.entity.CakeDesignOrderEntity
import com.cake.customcakebackend.domain.CakeDesignOrder
import org.springframework.stereotype.Component

@Component
class CakeDesignOrderMapper : Mapper<CakeDesignOrderEntity, CakeDesignOrder> {

    override fun toEntity(domain: CakeDesignOrder): CakeDesignOrderEntity =
        CakeDesignOrderEntity(
            id = domain.id,
            userId = domain.userId,
            cakeItemId = domain.cakeItemId,
            optionByCakeIdList = domain.optionByCakeIdList,
            requirements = domain.requirements,
            orderStatus = domain.orderStatus,
            paymentAmount = domain.paymentAmount,
            purchaseConfirmationDate = domain.purchaseConfirmationDate
        )

    override fun toDomain(entity: CakeDesignOrderEntity): CakeDesignOrder =
        CakeDesignOrder(
            id = entity.id,
            userId = entity.userId,
            cakeItemId = entity.cakeItemId,
            optionByCakeIdList = entity.optionByCakeIdList,
            requirements = entity.requirements,
            orderStatus = entity.orderStatus,
            paymentAmount = entity.paymentAmount,
            purchaseConfirmationDate = entity.purchaseConfirmationDate,
            createdAt = entity.createdAt,
            modifiedAt = entity.modifiedAt
        )
}