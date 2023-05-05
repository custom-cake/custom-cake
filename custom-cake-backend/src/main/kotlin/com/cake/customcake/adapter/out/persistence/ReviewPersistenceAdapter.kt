package com.cake.customcake.adapter.out.persistence

import com.cake.customcake.adapter.out.persistence.mapper.ReviewMapper
import com.cake.customcake.adapter.out.persistence.repository.ReviewJpaRepository
import com.cake.customcake.application.port.out.ReviewPort
import com.cake.customcake.domain.Review
import com.querydsl.core.Tuple
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import java.text.DecimalFormat
import com.cake.customcake.adapter.out.persistence.entity.QReviewEntity.reviewEntity as REVIEW
import com.cake.customcake.adapter.out.persistence.entity.QUserEntity.userEntity as USER

@Repository
class ReviewPersistenceAdapter(
    private val reviewMapper: ReviewMapper,
    private val reviewJpaRepository: ReviewJpaRepository,
    private val jpaQueryFactory: JPAQueryFactory
): ReviewPort {
    private val reviewFormat = DecimalFormat("#.#")
    override fun loadNickNameAndReviewList(storeId: Long): Map<String, Review> {
        val results: List<Tuple> = jpaQueryFactory
            .select(USER.nickname, REVIEW)
            .from(REVIEW)
            .join(USER).on(REVIEW.userId.eq(USER.id))
            .where(REVIEW.storeId.eq(storeId))
            .fetch()

        return results.associate {
            it.get(USER.nickname)!! to reviewMapper.toDomain(it.get(REVIEW)!!)
        }
    }

    override fun calculateReviewScore(storeId: Long): Float {
        val result: Pair<Int?, Long?>? = jpaQueryFactory
            .select(REVIEW.score.sum(), REVIEW.count())
            .from(REVIEW)
            .where(REVIEW.storeId.eq(storeId))
            .fetch()
            .map { it.get(0, Int::class.java) to it.get(1, Long::class.java) }.firstOrNull()

        result
            ?. let {
                val score = it.first ?: 0
                val count = it.second ?: 0L
                return if (count == 0L) 0F else {
                    reviewFormat.format(score / count).toFloat()
                }
            }
            ?: return 0F
    }
}