package com.cake.customcakebackend.adapter.out.persistence.repository

import com.cake.customcakebackend.adapter.`in`.web.dto.request.StoreOptionSearchRequest
import com.cake.customcakebackend.adapter.out.persistence.entity.QStoreEntity.storeEntity
import com.cake.customcakebackend.adapter.out.persistence.entity.StoreEntity
import com.cake.customcakebackend.common.DayOfWeekUnit
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class StoreQueryJpaRepository(
    val queryFactory: JPAQueryFactory
) {

    // TODO : 쿼리 최적화 필요 (can't search json in mysql)
    fun searchByOption(request: StoreOptionSearchRequest): List<StoreEntity> {
        val result: List<StoreEntity> = queryFactory
            .select(storeEntity)
            .from(storeEntity)
            .where(storeEntity.address.region.`in`(request.regions))
            .fetch()

        return result.filter { isOpen(request.days, it.openTime) }
    }

    private fun isOpen(days: List<DayOfWeekUnit>, openTime: Map<DayOfWeekUnit, String>): Boolean {
        for (day in days) {
            if (openTime.containsKey(day))
                return true
        }
        return false
    }
}