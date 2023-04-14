package com.cake.customcakebackend.adapter.out.persistence.mapper

import com.cake.customcakebackend.adapter.out.persistence.entity.ReviewEntity
import com.cake.customcakebackend.domain.Review
import org.springframework.stereotype.Component

@Component
class ReviewMapper : Mapper<ReviewEntity, Review> {
    override fun toEntity(domain: Review): ReviewEntity =
        ReviewEntity(
            id = domain.id,
            userId = domain.userId,
            storeId = domain.storeId,
            orderType = domain.orderType,
            orderOptionsInfo = domain.orderOptionsInfo,
            orderId = domain.orderId,
            content = domain.content,
            score = domain.score
        )

    override fun toDomain(entity: ReviewEntity): Review =
        Review(
            id = entity.id,
            userId = entity.userId,
            storeId = entity.storeId,
            orderType = entity.orderType,
            orderId = entity.orderId,
            orderOptionsInfo = entity.orderOptionsInfo,
            content = entity.content,
            score = entity.score,
            createdAt = entity.createdAt,
            modifiedAt = entity.modifiedAt
        )
}