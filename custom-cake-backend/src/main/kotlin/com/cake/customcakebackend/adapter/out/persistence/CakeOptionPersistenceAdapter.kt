package com.cake.customcakebackend.adapter.out.persistence

import com.cake.customcakebackend.adapter.out.persistence.mapper.CakeOptionMapper
import com.cake.customcakebackend.adapter.out.persistence.repository.CakeOptionJpaRepository
import com.cake.customcakebackend.application.port.out.CakeOptionPort
import com.cake.customcakebackend.domain.CakeOption
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class CakeOptionPersistenceAdapter(
    private val cakeOptionMapper: CakeOptionMapper,
    private val jpaQueryFactory: JPAQueryFactory,
    private val cakeOptionJpaRepository: CakeOptionJpaRepository
) : CakeOptionPort {
    override fun loadInfo(cakeOptionType: Long, cakeOptionId: Long): CakeOption {
        TODO("Not yet implemented")
    }

    override fun loadList(storeId: Long): List<CakeOption> {
        TODO("Not yet implemented")
    }

    override fun save(): Pair<Long, Long> {
        TODO("Not yet implemented")
    }

    override fun modify(): Pair<Long, Long> {
        TODO("Not yet implemented")
    }

    override fun delete() {
        TODO("Not yet implemented")
    }
}