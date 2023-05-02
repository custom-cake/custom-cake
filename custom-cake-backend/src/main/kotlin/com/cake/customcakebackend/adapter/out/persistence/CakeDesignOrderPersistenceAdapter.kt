package com.cake.customcakebackend.adapter.out.persistence

import com.cake.customcakebackend.adapter.out.persistence.entity.QCakeDesignOrderEntity.cakeDesignOrderEntity as CAKE_DESIGN_ORDER
import com.cake.customcakebackend.adapter.out.persistence.mapper.CakeDesignOrderMapper
import com.cake.customcakebackend.adapter.out.persistence.repository.CakeDesignOrderRepository
import com.cake.customcakebackend.application.port.out.CakeDesignOrderPort
import com.cake.customcakebackend.domain.CakeDesignOrder
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component

@Component
class CakeDesignOrderPersistenceAdapter(
    private val cakeDesignOrderMapper: CakeDesignOrderMapper,
    private val cakeDesignOrderRepository: CakeDesignOrderRepository,
    private val jpaQueryFactory: JPAQueryFactory,
) : CakeDesignOrderPort {

    override fun save(cakeDesignOrder: CakeDesignOrder) {
        cakeDesignOrderRepository.save(cakeDesignOrderMapper.toEntity(cakeDesignOrder))
    }

    override fun loadList(userId: Long): List<CakeDesignOrder> =
        // 유저의 디자인 케이크 주문 내역 get
        jpaQueryFactory
            .selectFrom(CAKE_DESIGN_ORDER)
            .where(CAKE_DESIGN_ORDER.userId.eq(userId))
            .fetch()
            .map { cakeDesignOrderMapper.toDomain(it) }
}