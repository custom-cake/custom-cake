package com.cake.customcakebackend.adapter.out.persistence.mapper

import com.cake.customcakebackend.adapter.out.persistence.entity.Address
import com.cake.customcakebackend.adapter.out.persistence.entity.StoreEntity
import com.cake.customcakebackend.domain.Store
import org.springframework.stereotype.Component

@Component
class StoreMapper : Mapper<StoreEntity, Store>{
    override fun toEntity(domain: Store): StoreEntity =
        StoreEntity(
            id = domain.id,
            operatorId = domain.operatorId,
            businessRegistrationNo = domain.businessRegistrationNo,
            representativeName = domain.representativeName,
            address = Address(domain.zipCode, domain.baseAddress, domain.detailAddress),
            phone = domain.phone,
            name = domain.name,
            description = domain.description,
            openTime = domain.openTime,
            reservationPeriod = domain.reservationPeriod,
            reservationPerPeriodCount = domain.reservationPerPeriodCount,
            thumbnailImageUrl = domain.thumbnailImageUrl,
            viewCount = domain.viewCount,
            ratingSum = domain.ratingSum
        )

    override fun toDomain(entity: StoreEntity): Store =
        Store(
            id = entity.id,
            operatorId = entity.id,
            businessRegistrationNo = entity.businessRegistrationNo,
            representativeName = entity.representativeName,
            zipCode = entity.address.zipCode,
            baseAddress = entity.address.baseAddress,
            detailAddress = entity.address.detailAddress,
            phone = entity.phone,
            name = entity.name,
            description = entity.description,
            openTime = entity.openTime,
            reservationPeriod = entity.reservationPeriod,
            reservationPerPeriodCount = entity.reservationPerPeriodCount,
            thumbnailImageUrl = entity.thumbnailImageUrl,
            viewCount = entity.viewCount,
            ratingSum = entity.ratingSum,
            createdAt = entity.createdAt,
            modifiedAt = entity.modifiedAt
        )
}