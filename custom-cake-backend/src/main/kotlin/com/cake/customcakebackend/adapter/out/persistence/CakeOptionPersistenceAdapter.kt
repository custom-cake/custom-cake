package com.cake.customcakebackend.adapter.out.persistence

import com.cake.customcakebackend.adapter.out.persistence.entity.QCakeOption1Entity.cakeOption1Entity
import com.cake.customcakebackend.adapter.out.persistence.entity.QCakeOption2Entity.cakeOption2Entity
import com.cake.customcakebackend.adapter.out.persistence.entity.QCakeOption3Entity.cakeOption3Entity
import com.cake.customcakebackend.adapter.out.persistence.mapper.CakeOptionMapper
import com.cake.customcakebackend.adapter.out.persistence.repository.CakeOptionJpaRepository
import com.cake.customcakebackend.application.port.out.CakeOptionPort
import com.cake.customcakebackend.domain.CakeOption
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class CakeOptionPersistenceAdapter(
    private val cakeOptionMapper: CakeOptionMapper,
    private val jpaQueryFactory: JPAQueryFactory,
    private val cakeOptionJpaRepository: CakeOptionJpaRepository
) : CakeOptionPort {
    override fun loadInfo(cakeOptionType: Int, cakeOptionId: Long): CakeOption {
        TODO("Not yet implemented")
    }

    override fun loadAllCakeOptionList(storeId: Long): Map<Int, List<CakeOption>> {
        val cakeOption1Entities = jpaQueryFactory
            .selectFrom(cakeOption1Entity)
            .where(
                cakeOption1Entity.storeId.eq(storeId),
                cakeOption1Entity.isDeleted.isFalse)
            .fetch()
        val cakeOption2Entities = jpaQueryFactory
            .selectFrom(cakeOption2Entity)
            .where(
                cakeOption2Entity.storeId.eq(storeId),
                cakeOption2Entity.isDeleted.isFalse)
            .fetch()
        val cakeOption3Entities = jpaQueryFactory
            .selectFrom(cakeOption3Entity)
            .where(
                cakeOption3Entity.storeId.eq(storeId),
                cakeOption3Entity.isDeleted.isFalse)
            .fetch()
        val cakeOptionEntities = cakeOption1Entities.plus(cakeOption2Entities).plus(cakeOption3Entities)

        return cakeOptionEntities.groupBy { it.getType() }
            .map { it.key to it.value.map { domain -> cakeOptionMapper.toDomain(domain) } }
            .toMap()
    }

    override fun loadCakeOptionList(storeId: Long, cakeOptionType: Int): List<CakeOption> {
        TODO("Not yet implemented")
    }

    override fun save(cakeOptionType: Int, cakeOption: CakeOption): Pair<Int, Long> {
        val cakeOptionEntity = cakeOptionMapper.toEntity(cakeOption)
        val savedEntity = cakeOptionJpaRepository.save(cakeOptionEntity)
        return cakeOptionType to savedEntity.id
    }

    override fun modify(): Pair<Long, Long> {
        TODO("Not yet implemented")
    }

    override fun delete(cakeOptionType: Int, optionId: Long) {
        when (cakeOptionType) {
            1 -> {
                jpaQueryFactory
                    .update(cakeOption1Entity)
                    .set(cakeOption1Entity.isDeleted, true)
                    .where(cakeOption1Entity.id.eq(optionId))
                    .execute()
            }
            2 -> {
                jpaQueryFactory
                    .update(cakeOption2Entity)
                    .set(cakeOption2Entity.isDeleted, true)
                    .where(cakeOption2Entity.id.eq(optionId))
                    .execute()
            }
            3 -> {
                jpaQueryFactory
                    .update(cakeOption3Entity)
                    .set(cakeOption3Entity.isDeleted, true)
                    .where(cakeOption3Entity.id.eq(optionId))
                    .execute()
            }
            else -> {}
        }
    }
}