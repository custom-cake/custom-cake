package com.cake.customcakebackend.adapter.out.persistence.mapper

import com.cake.customcakebackend.adapter.out.persistence.entity.CakeOption1Entity
import com.cake.customcakebackend.adapter.out.persistence.entity.CakeOption2Entity
import com.cake.customcakebackend.adapter.out.persistence.entity.CakeOption3Entity
import com.cake.customcakebackend.adapter.out.persistence.entity.CakeOptionEntity
import com.cake.customcakebackend.domain.CakeOption
import com.cake.customcakebackend.domain.CakeOption1
import com.cake.customcakebackend.domain.CakeOption2
import com.cake.customcakebackend.domain.CakeOption3
import org.springframework.stereotype.Component

@Component
class CakeOptionMapper : Mapper<CakeOptionEntity, CakeOption> {
    override fun toEntity(domain: CakeOption): CakeOptionEntity {
        TODO("Not yet implemented")
    }

    override fun toDomain(entity: CakeOptionEntity): CakeOption {
        return when {
            (entity is CakeOption1Entity) ->
                CakeOption1(
                    id = entity.id,
                    storeId = entity.storeId,
                    cakeShape = entity.cakeShape,
                    cakeLayer = entity.cakeLayer,
                    cakeSize = entity.cakeSize,
                    letteringLimit = entity.letteringLimit,
                    price = entity.price,
                    isUsed = entity.isUsed,
                    isDeleted = entity.isDeleted,
                    createdAt = entity.createdAt,
                    modifiedAt = entity.modifiedAt
                )
            (entity is CakeOption2Entity) ->
                CakeOption2(
                    id = entity.id,
                    storeId = entity.storeId,
                    cakeSheet = entity.cakeSheet,
                    cakeInnerCream = entity.cakeInnerCream,
                    cakeOuterCream = entity.cakeOuterCream,
                    price = entity.price,
                    isUsed = entity.isUsed,
                    isDeleted = entity.isDeleted,
                    createdAt = entity.createdAt,
                    modifiedAt = entity.modifiedAt
                )
            (entity is CakeOption3Entity) ->
                CakeOption3(
                    id = entity.id,
                    storeId = entity.storeId,
                    name = entity.name,
                    price = entity.price,
                    isUsed = entity.isUsed,
                    isDeleted = entity.isDeleted,
                    createdAt = entity.createdAt,
                    modifiedAt = entity.modifiedAt
                )
            else -> throw Exception("")
        }
    }
}