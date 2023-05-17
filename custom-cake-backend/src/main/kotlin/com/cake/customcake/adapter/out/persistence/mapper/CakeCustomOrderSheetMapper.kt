package com.cake.customcake.adapter.out.persistence.mapper

import com.cake.customcake.adapter.out.persistence.entity.CakeCustomOrderSheetEntity
import com.cake.customcake.domain.CakeCustomOrderSheet
import org.springframework.stereotype.Component

@Component
class CakeCustomOrderSheetMapper : Mapper<CakeCustomOrderSheetEntity, CakeCustomOrderSheet> {

    override fun toEntity(domain: CakeCustomOrderSheet): CakeCustomOrderSheetEntity =
        CakeCustomOrderSheetEntity(
            id = domain.id,
            userId = domain.userId,
            storeId = domain.storeId,
            cakeCustomImageUrl = domain.cakeCustomImageUrl,
            option1Id = domain.option1Id,
            option2Id = domain.option2Id,
            option3IdList = domain.option3IdList,
            userRequirements = domain.userRequirements,
            operatorRequirements = domain.otherRequirements,
            paymentAmount = domain.paymentAmount,
            pickupDatetime = domain.pickupDatetime
        )

    override fun toDomain(entity: CakeCustomOrderSheetEntity): CakeCustomOrderSheet =
        CakeCustomOrderSheet(
            id = entity.id,
            userId = entity.userId,
            storeId = entity.storeId,
            cakeCustomImageUrl = entity.cakeCustomImageUrl,
            option1Id = entity.option1Id,
            option2Id = entity.option2Id,
            option3IdList = entity.option3IdList,
            userRequirements = entity.userRequirements,
            otherRequirements = entity.operatorRequirements,
            paymentAmount = entity.paymentAmount,
            pickupDatetime = entity.pickupDatetime,
            createdAt = entity.createdAt,
            modifiedAt = entity.modifiedAt
        )
}