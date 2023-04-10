package com.cake.customcakebackend.adapter.out.persistence

import com.cake.customcakebackend.adapter.out.persistence.mapper.ReviewMapper
import com.cake.customcakebackend.adapter.out.persistence.repository.ReviewJpaRepository
import com.cake.customcakebackend.application.port.out.ReviewPort
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import com.cake.customcakebackend.adapter.out.persistence.entity.QReviewEntity.reviewEntity as review

@Repository
class ReviewPersistenceAdapter(
    private val reviewMapper: ReviewMapper,
    private val reviewJpaRepository: ReviewJpaRepository,
    private val jpaQueryFactory: JPAQueryFactory
): ReviewPort {
    override fun calculateReviewScore(storeId: Long): Float {
        val result: Pair<Int, Int>? = jpaQueryFactory.select(review.score.sum(), review.count()).from(review)
            .where(review.storeId.eq(storeId))
            .fetch()
            .map { it.get(0, Int::class.java)!! to it.get(1, Int::class.java)!! }.firstOrNull()

        result
            ?. let {
                return if (it.second == 0) 0F else it.first.div(it.second).toFloat()
            }
            ?: return 0F
    }
}