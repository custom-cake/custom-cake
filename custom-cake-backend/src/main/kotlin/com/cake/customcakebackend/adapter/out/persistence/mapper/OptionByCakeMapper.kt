package com.cake.customcakebackend.adapter.out.persistence.mapper

import com.cake.customcakebackend.adapter.out.persistence.entity.OptionByCakeEntity
import com.cake.customcakebackend.domain.OptionByCake
import org.springframework.stereotype.Component

@Component
class OptionByCakeMapper : Mapper<OptionByCakeEntity, OptionByCake> {
    override fun toEntity(domain: OptionByCake): OptionByCakeEntity =
        OptionByCakeEntity(
            id = domain.id,
            cakeItemId = domain.cakeItemId,
            cakeOptionType = domain.cakeOptionType,
            cakeOptionId = domain.cakeOptionId,
            cakeOptionValue = domain.cakeOptionValue,
            price = domain.price,
            isUsed = domain.isUsed
        )

    override fun toDomain(entity: OptionByCakeEntity): OptionByCake =
        OptionByCake(
            id = entity.id,
            cakeItemId = entity.cakeItemId,
            cakeOptionType = entity.cakeOptionType,
            cakeOptionId = entity.cakeOptionId,
            cakeOptionValue = entity.cakeOptionValue,
            price = entity.price,
            isUsed = entity.isUsed,
            createdAt = entity.createdAt,
            modifiedAt = entity.modifiedAt
        )
}