package com.cake.customcake.adapter.out.persistence.mapper

import com.cake.customcake.adapter.out.persistence.entity.StoreNotificationEntity
import com.cake.customcake.domain.StoreNotification
import org.springframework.stereotype.Component

@Component
class StoreNotificationMapper: Mapper<StoreNotificationEntity, StoreNotification> {
    override fun toEntity(domain: StoreNotification): StoreNotificationEntity =
        StoreNotificationEntity(
            id = domain.id,
            storeId = domain.storeId,
            title = domain.title,
            content = domain.content
        )

    override fun toDomain(entity: StoreNotificationEntity): StoreNotification =
        StoreNotification(
            id = entity.id,
            storeId = entity.storeId,
            title = entity.title,
            content = entity.content,
            createdAt = entity.createdAt,
            modifiedAt = entity.modifiedAt
        )
}