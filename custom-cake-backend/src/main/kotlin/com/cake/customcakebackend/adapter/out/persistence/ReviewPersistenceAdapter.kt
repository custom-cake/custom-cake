package com.cake.customcakebackend.adapter.out.persistence

import com.cake.customcakebackend.adapter.out.persistence.mapper.ReviewMapper
import com.cake.customcakebackend.adapter.out.persistence.repository.ReviewJpaRepository
import com.cake.customcakebackend.application.port.out.ReviewPort
import com.cake.customcakebackend.domain.Review
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import java.text.DecimalFormat
import com.cake.customcakebackend.adapter.out.persistence.entity.QReviewEntity.reviewEntity as review

@Repository
class ReviewPersistenceAdapter(
    private val reviewMapper: ReviewMapper,
    private val reviewJpaRepository: ReviewJpaRepository,
    private val jpaQueryFactory: JPAQueryFactory
): ReviewPort {
    private val reviewFormat = DecimalFormat("#.#")
    override fun loadList(storeId: Long): List<Review> =
        jpaQueryFactory
            .selectFrom(review)
            .where(review.storeId.eq(storeId))
            .fetch()
            .map { reviewMapper.toDomain(it) }

    override fun calculateReviewScore(storeId: Long): Float {
        val result: Pair<Long?, Long?>? = jpaQueryFactory
            .select(review.score.sum(), review.count())
            .from(review)
            .where(review.storeId.eq(storeId))
            .fetch()
            .map { it.get(0, Long::class.java) to it.get(1, Long::class.java) }.firstOrNull()

        result
            ?. let {
                val score = it.first ?: 0
                val count = it.second ?: 0
                return if (count == 0L) 0F else {
                    reviewFormat.format(score / count).toFloat()
                }
            }
            ?: return 0F
    }
}