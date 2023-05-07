package com.cake.customcake.adapter.out.persistence.repository

import com.cake.customcake.adapter.`in`.web.dto.request.StoreOptionSearchRequest
import com.cake.customcake.adapter.out.persistence.entity.DayoffEntity
import com.cake.customcake.adapter.out.persistence.entity.QDayoffEntity.*
import com.cake.customcake.adapter.out.persistence.entity.QStoreEntity.storeEntity
import com.cake.customcake.adapter.out.persistence.entity.StoreEntity
import com.cake.customcake.common.DayOfWeekUnit
import com.cake.customcake.common.DayoffType
import com.querydsl.core.Tuple
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class StoreQueryJpaRepository(
    val queryFactory: JPAQueryFactory
) {

    // TODO : 쿼리 최적화 필요
    fun searchByOption(request: StoreOptionSearchRequest): List<StoreEntity> {
        val result: List<Tuple> = queryFactory
            .select(storeEntity, dayoffEntity)
            .from(storeEntity)
            .where(storeEntity.address.region.`in`(request.regions))
            .leftJoin(dayoffEntity).on(dayoffEntity.storeId.eq(storeEntity.id))
            .fetch()

        val resultMap: Map<StoreEntity, List<DayoffEntity>> = result.groupBy(
            { it.get(0, StoreEntity::class.java)!! },
            { it.get(1, DayoffEntity::class.java)!! }
        )

        return resultMap.keys.filter { isOpen(request.days, it.openTime, resultMap[it]!!) }
    }

    private fun isOpen(
        days: List<LocalDate>,
        openTime: Map<DayOfWeekUnit, String>,
        dayOffEntities: List<DayoffEntity>
    ): Boolean {
        val dayOfWeekUnits = days.map { DayOfWeekUnit.of(it) }
        if (dayOfWeekUnits.all { !openTime.containsKey(it) }) return false
        if (days.any { checkDay(it, dayOffEntities) }) return true
        return false
    }

    private fun checkDay(day: LocalDate, dayOffEntities: List<DayoffEntity>): Boolean {
        println(
            dayOffEntities.any {
                when (it.dayoffType) {
                    DayoffType.FIXED -> it.dayoffDay == DayOfWeekUnit.of(day)
                    DayoffType.DESIGNATED -> it.dayoffDate == day
                }
            }.toString()
        )
        return !dayOffEntities.any {
            when (it.dayoffType) {
                DayoffType.FIXED -> it.dayoffDay == DayOfWeekUnit.of(day)
                DayoffType.DESIGNATED -> it.dayoffDate == day
            }
        }
    }
}