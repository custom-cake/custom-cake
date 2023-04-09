package com.cake.customcakebackend.adapter.out.persistence

import com.cake.customcakebackend.adapter.out.persistence.entity.CakeOptionEntity
import com.cake.customcakebackend.adapter.out.persistence.entity.QCakeOption1Entity.cakeOption1Entity
import com.cake.customcakebackend.adapter.out.persistence.entity.QCakeOption2Entity.cakeOption2Entity
import com.cake.customcakebackend.adapter.out.persistence.entity.QCakeOption3Entity.cakeOption3Entity
import com.cake.customcakebackend.adapter.out.persistence.entity.QCakeOptionEntity.cakeOptionEntity
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

    override fun loadAllCakeOptionList(storeId: Long): Map<Int, List<CakeOption>> {
        val cakeOption1Entities = jpaQueryFactory
            .selectFrom(cakeOption1Entity)
            .where(cakeOption1Entity.storeId.eq(storeId))
            .fetch()
        val cakeOption2Entities = jpaQueryFactory
            .selectFrom(cakeOption2Entity)
            .where(cakeOption2Entity.storeId.eq(storeId))
            .fetch()
        val cakeOption3Entities = jpaQueryFactory
            .selectFrom(cakeOption3Entity)
            .where(cakeOption3Entity.storeId.eq(storeId))
            .fetch()
        val cakeOptionEntities = cakeOption1Entities.plus(cakeOption2Entities).plus(cakeOption3Entities)

        return cakeOptionEntities.groupBy { it.getType() }
            .map { it.key to it.value.map { cakeOptionMapper.toDomain(it) } }
            .toMap()
    }

    override fun loadCakeOptionList(storeId: Long, cakeOptionType: Long): List<CakeOption> {
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