package com.cake.customcake.adapter.out.persistence.mapper

import com.cake.customcake.adapter.out.persistence.entity.CakeOption1Entity
import com.cake.customcake.adapter.out.persistence.entity.CakeOption2Entity
import com.cake.customcake.adapter.out.persistence.entity.CakeOption3Entity
import com.cake.customcake.adapter.out.persistence.entity.CakeOptionEntity
import com.cake.customcake.domain.CakeOption
import com.cake.customcake.domain.CakeOption1
import com.cake.customcake.domain.CakeOption2
import com.cake.customcake.domain.CakeOption3
import org.springframework.stereotype.Component

@Component
class CakeOptionMapper : Mapper<CakeOptionEntity, CakeOption> {
    override fun toEntity(domain: CakeOption): CakeOptionEntity {
        return when {
            (domain is CakeOption1) ->
                CakeOption1Entity(
                    storeId = domain.storeId,
                    cakeShape = domain.cakeShape,
                    cakeLayer = domain.cakeLayer,
                    cakeSize = domain.cakeSize,
                    letteringLimit = domain.letteringLimit,
                    price = domain.price,
                    isUsed = domain.isUsed,
                    isDeleted = domain.isDeleted,
                )
            (domain is CakeOption2) ->
                CakeOption2Entity(
                    storeId = domain.storeId,
                    cakeSheet = domain.cakeSheet,
                    cakeInnerCream = domain.cakeInnerCream,
                    cakeOuterCream = domain.cakeOuterCream,
                    price = domain.price,
                    isUsed = domain.isUsed,
                    isDeleted = domain.isDeleted,
                )
            (domain is CakeOption3) ->
                CakeOption3Entity(
                    storeId = domain.storeId,
                    name = domain.name,
                    price = domain.price,
                    isUsed = domain.isUsed,
                    isDeleted = domain.isDeleted,
                )
            else -> throw Exception("")
        }
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