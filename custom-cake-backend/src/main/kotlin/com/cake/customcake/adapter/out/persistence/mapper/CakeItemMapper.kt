package com.cake.customcake.adapter.out.persistence.mapper

import com.cake.customcake.adapter.out.persistence.entity.CakeItemEntity
import com.cake.customcake.domain.CakeItem
import org.springframework.stereotype.Component

@Component
class CakeItemMapper : Mapper<CakeItemEntity, CakeItem> {
    override fun toEntity(domain: CakeItem): CakeItemEntity =
        CakeItemEntity(
            id = domain.id,
            storeId = domain.storeId,
            name = domain.name,
            category = domain.category,
            description = domain.description,
            thumbnailImageUrl = domain.thumbnailImageUrl,
            isOnsale = domain.isOnsale,
            isDeleted = domain.isDeleted,
            price = domain.price,
            viewCount = domain.viewCount,
            orderCount = domain.orderCount
        )


    override fun toDomain(entity: CakeItemEntity): CakeItem =
        CakeItem(
            id = entity.id,
            storeId = entity.storeId,
            name = entity.name,
            category = entity.category,
            description = entity.description,
            thumbnailImageUrl = entity.thumbnailImageUrl,
            isOnsale = entity.isOnsale,
            isDeleted = entity.isDeleted,
            price = entity.price,
            viewCount = entity.viewCount,
            orderCount = entity.orderCount,
            createdAt = entity.createdAt,
            modifiedAt = entity.modifiedAt
        )
}