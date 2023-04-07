package com.cake.customcakebackend.adapter.out.persistence

import com.cake.customcakebackend.adapter.out.persistence.mapper.DayoffMapper
import com.cake.customcakebackend.adapter.out.persistence.repository.DayoffJpaRepository
import com.cake.customcakebackend.application.port.out.DayoffPort
import com.cake.customcakebackend.domain.Dayoff
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class DayoffPersistenceAdapter(
    private val dayoffMapper: DayoffMapper,
    private val dayoffJpaRepository: DayoffJpaRepository
) : DayoffPort
{
    override fun saveFixedDayoff(fixedDayoffList: List<Dayoff>) {
        dayoffJpaRepository.saveAll(
            fixedDayoffList.map { dayoffMapper.toEntity(it) }
        )
    }

    override fun saveDesignedDayoff(designedDayoffList: List<Date>) {
        TODO("Not yet implemented")
    }
}