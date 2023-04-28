package com.cake.customcakebackend.adapter.out.persistence

import com.cake.customcakebackend.adapter.out.persistence.mapper.OptionByCakeMapper
import com.cake.customcakebackend.application.port.out.OptionByCakePort
import com.cake.customcakebackend.domain.OptionByCake
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import com.cake.customcakebackend.adapter.out.persistence.entity.QOptionByCakeEntity.optionByCakeEntity as OPTION_BY_CAKE

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
            .where(OPTION_BY_CAKE.cakeItemId.eq(cakeItemId))
            .where(OPTION_BY_CAKE.id.`in`(optionByCakeIdList))
            .fetch()
            .map { optionByCakeMapper.toDomain(it) }
}