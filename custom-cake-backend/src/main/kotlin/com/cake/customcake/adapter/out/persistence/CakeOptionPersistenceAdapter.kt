package com.cake.customcake.adapter.out.persistence

import com.cake.customcake.adapter.out.persistence.entity.CakeOptionEntity
import com.cake.customcake.adapter.out.persistence.entity.QCakeOption1Entity.cakeOption1Entity as CAKE_OPTION_1
import com.cake.customcake.adapter.out.persistence.entity.QCakeOption2Entity.cakeOption2Entity as CAKE_OPTION_2
import com.cake.customcake.adapter.out.persistence.entity.QCakeOption3Entity.cakeOption3Entity as CAKE_OPTION_3
import com.cake.customcake.adapter.out.persistence.mapper.CakeOptionMapper
import com.cake.customcake.adapter.out.persistence.repository.CakeOptionJpaRepository
import com.cake.customcake.application.port.out.CakeOptionPort
import com.cake.customcake.domain.CakeOption
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

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
            .selectFrom(CAKE_OPTION_1)
            .where(
                CAKE_OPTION_1.storeId.eq(storeId),
                CAKE_OPTION_1.isDeleted.isFalse)
            .fetch()
        val cakeOption2Entities = jpaQueryFactory
            .selectFrom(CAKE_OPTION_2)
            .where(
                CAKE_OPTION_2.storeId.eq(storeId),
                CAKE_OPTION_2.isDeleted.isFalse)
            .fetch()
        val cakeOption3Entities = jpaQueryFactory
            .selectFrom(CAKE_OPTION_3)
            .where(
                CAKE_OPTION_3.storeId.eq(storeId),
                CAKE_OPTION_3.isDeleted.isFalse)
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
                    .update(CAKE_OPTION_1)
                    .set(CAKE_OPTION_1.isDeleted, true)
                    .where(CAKE_OPTION_1.id.eq(optionId))
                    .execute()
            }
            2 -> {
                jpaQueryFactory
                    .update(CAKE_OPTION_2)
                    .set(CAKE_OPTION_2.isDeleted, true)
                    .where(CAKE_OPTION_2.id.eq(optionId))
                    .execute()
            }
            3 -> {
                jpaQueryFactory
                    .update(CAKE_OPTION_3)
                    .set(CAKE_OPTION_3.isDeleted, true)
                    .where(CAKE_OPTION_3.id.eq(optionId))
                    .execute()
            }
            else -> {}
        }
    }

    override fun loadListByIdList(optionIdList: List<Long>): List<String> {
        val cakeOption1Entity = jpaQueryFactory
            .selectFrom(CAKE_OPTION_1)
            .where(
                CAKE_OPTION_1.id.eq(optionIdList[0]),
                CAKE_OPTION_1.isDeleted.isFalse
            )
            .fetchFirst()
        val cakeOption2Entity = jpaQueryFactory
            .selectFrom(CAKE_OPTION_2)
            .where(
                CAKE_OPTION_2.id.eq(optionIdList[1]),
                CAKE_OPTION_2.isDeleted.isFalse
            )
            .fetchFirst()
        val cakeOption3Entity = jpaQueryFactory
            .selectFrom(CAKE_OPTION_3)
            .where(
                CAKE_OPTION_3.id.eq(optionIdList[2]),
                CAKE_OPTION_3.isDeleted.isFalse
            )
            .fetchFirst()
        return listOfNotNull(cakeOption1Entity, cakeOption2Entity, cakeOption3Entity)
            .map { cakeOptionMapper.toDomain(it).toResponse().value }
    }
}