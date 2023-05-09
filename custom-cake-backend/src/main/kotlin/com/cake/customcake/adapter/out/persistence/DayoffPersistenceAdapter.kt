package com.cake.customcake.adapter.out.persistence

import com.cake.customcake.adapter.out.persistence.entity.QDayoffEntity
import com.cake.customcake.adapter.out.persistence.mapper.DayoffMapper
import com.cake.customcake.adapter.out.persistence.repository.DayoffJpaRepository
import com.cake.customcake.application.port.out.DayoffPort
import com.cake.customcake.domain.Dayoff
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class DayoffPersistenceAdapter(
    private val dayoffMapper: DayoffMapper,
    private val dayoffJpaRepository: DayoffJpaRepository,
    private val jpaQueryFactory: JPAQueryFactory
) : DayoffPort
{
    override fun loadDayOff(storeId: Long): List<Dayoff> =
        jpaQueryFactory
            .selectFrom(QDayoffEntity.dayoffEntity)
            .where(QDayoffEntity.dayoffEntity.storeId.eq(storeId))
            .fetch()
            .map { dayoffMapper.toDomain(it) }

    override fun saveFixedDayoff(fixedDayoffList: List<Dayoff>) {
        dayoffJpaRepository.saveAll(
            fixedDayoffList.map { dayoffMapper.toEntity(it) }
        )
    }

    override fun saveDesignedDayoff(designedDayoffList: List<Date>) {
        TODO("Not yet implemented")
    }


}