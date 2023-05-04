package com.cake.customcakebackend.adapter.out.persistence.mapper

import com.cake.customcakebackend.adapter.out.persistence.entity.StoreGalleryEntity
import com.cake.customcakebackend.domain.StoreGallery
import org.springframework.stereotype.Component

@Component
class StoreGalleryMapper: Mapper<StoreGalleryEntity, StoreGallery> {
    override fun toEntity(domain: StoreGallery): StoreGalleryEntity =
        StoreGalleryEntity(
            storeId = domain.storeId,
            imageUrlList = domain.imageUrlList
        )

    override fun toDomain(entity: StoreGalleryEntity): StoreGallery =
        StoreGallery(
            id = entity.id,
            storeId = entity.storeId,
            imageUrlList = entity.imageUrlList,
            createdAt = entity.createdAt,
            modifiedAt = entity.modifiedAt
        )
}