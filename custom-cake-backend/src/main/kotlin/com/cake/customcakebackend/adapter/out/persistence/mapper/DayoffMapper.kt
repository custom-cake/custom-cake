package com.cake.customcakebackend.adapter.out.persistence.mapper

import com.cake.customcakebackend.adapter.out.persistence.entity.DayoffEntity
import com.cake.customcakebackend.domain.Dayoff
import org.springframework.stereotype.Component

@Component
class DayoffMapper : Mapper<DayoffEntity, Dayoff> {
    override fun toEntity(domain: Dayoff): DayoffEntity =
        DayoffEntity(
            id = domain.id,
            storeId = domain.storeId,
            dayoffDate = domain.dayoffDate,
            dayoffType = domain.dayoffType,
            dayoffDay = domain.dayoffDay
        )

    override fun toDomain(entity: DayoffEntity): Dayoff =
        Dayoff(
            id = entity.id,
            storeId = entity.storeId,
            dayoffDate = entity.dayoffDate,
            dayoffType = entity.dayoffType,
            dayoffDay = entity.dayoffDay,
            createdAt = entity.createdAt,
            modifiedAt = entity.modifiedAt
        )
}