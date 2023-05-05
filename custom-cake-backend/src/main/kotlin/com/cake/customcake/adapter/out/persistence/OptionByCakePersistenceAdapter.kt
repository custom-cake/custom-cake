package com.cake.customcake.adapter.out.persistence

import com.cake.customcake.adapter.out.persistence.mapper.OptionByCakeMapper
import com.cake.customcake.application.port.out.OptionByCakePort
import com.cake.customcake.domain.OptionByCake
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import com.cake.customcake.adapter.out.persistence.entity.QOptionByCakeEntity.optionByCakeEntity as OPTION_BY_CAKE

@Repository
class OptionByCakePersistenceAdapter(
    val optionByCakeMapper: OptionByCakeMapper,
    val jpaQueryFactory: JPAQueryFactory
): OptionByCakePort {
    override fun loadList(cakeItemId: Long): List<OptionByCake> =
        jpaQueryFactory
            .selectFrom(OPTION_BY_CAKE)
            .where(OPTION_BY_CAKE.cakeItemId.eq(cakeItemId))
            .fetch()
            .map { optionByCakeMapper.toDomain(it) }

    override fun loadListByIdList(optionByCakeIdList: List<Long>, cakeItemId: Long): List<OptionByCake> =
        jpaQueryFactory
            .selectFrom(OPTION_BY_CAKE)
            .where(
                OPTION_BY_CAKE.cakeItemId.eq(cakeItemId),
                OPTION_BY_CAKE.id.`in`(optionByCakeIdList)
            )
            .fetch()
            .map { optionByCakeMapper.toDomain(it) }

    override fun loadListByIdList(optionByCakeIdList: List<Long>): List<String> {
        return jpaQueryFactory
            .select(OPTION_BY_CAKE.cakeOptionValue)
            .from(OPTION_BY_CAKE)
            .where(
                OPTION_BY_CAKE.id.`in`(optionByCakeIdList.map { it.toLong() })
            )
            .fetch()
    }
}