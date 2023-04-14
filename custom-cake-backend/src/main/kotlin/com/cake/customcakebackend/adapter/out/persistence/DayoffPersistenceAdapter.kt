package com.cake.customcakebackend.adapter.out.persistence

import com.cake.customcakebackend.adapter.out.persistence.entity.QDayoffEntity
import com.cake.customcakebackend.adapter.out.persistence.mapper.DayoffMapper
import com.cake.customcakebackend.adapter.out.persistence.repository.DayoffJpaRepository
import com.cake.customcakebackend.application.port.out.DayoffPort
import com.cake.customcakebackend.domain.Dayoff
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