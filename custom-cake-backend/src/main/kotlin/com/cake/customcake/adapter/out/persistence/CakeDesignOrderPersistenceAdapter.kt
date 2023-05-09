package com.cake.customcake.adapter.out.persistence

import com.cake.customcake.adapter.out.persistence.entity.QCakeDesignOrderEntity.cakeDesignOrderEntity as CAKE_DESIGN_ORDER
import com.cake.customcake.adapter.out.persistence.mapper.CakeDesignOrderMapper
import com.cake.customcake.adapter.out.persistence.repository.CakeDesignOrderRepository
import com.cake.customcake.application.port.out.CakeDesignOrderPort
import com.cake.customcake.common.OrderStatus
import com.cake.customcake.domain.CakeDesignOrder
import com.querydsl.jpa.impl.JPAQueryFactory
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class CakeDesignOrderPersistenceAdapter(
    private val cakeDesignOrderMapper: CakeDesignOrderMapper,
    private val cakeDesignOrderRepository: CakeDesignOrderRepository,
    private val jpaQueryFactory: JPAQueryFactory,
) : CakeDesignOrderPort {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    override fun save(cakeDesignOrder: CakeDesignOrder) {
        cakeDesignOrderRepository.save(cakeDesignOrderMapper.toEntity(cakeDesignOrder))
    }

    override fun loadListByUserId(userId: Long): List<CakeDesignOrder> =
        // 유저의 디자인 케이크 주문 내역
        jpaQueryFactory
            .selectFrom(CAKE_DESIGN_ORDER)
            .where(CAKE_DESIGN_ORDER.userId.eq(userId))
            .fetch()
            .map { cakeDesignOrderMapper.toDomain(it) }

    override fun loadListByStoreId(storeId: Long, orderStatus: OrderStatus): List<CakeDesignOrder> =
        // 매장의 디자인 케이크 주문 내역
        jpaQueryFactory
            .selectFrom(CAKE_DESIGN_ORDER)
            .where(CAKE_DESIGN_ORDER.storeId.eq(storeId),
                CAKE_DESIGN_ORDER.orderStatus.eq(orderStatus))
            .fetch()
            .map { cakeDesignOrderMapper.toDomain(it) }

    override fun approveCakeOrder(orderId: Long) {
        // 케이크 주문 승인 시 orderStatus NEW -> IN_PROGRESS 변경
        // check orderStatus == NEW
        val orderStatus = jpaQueryFactory
            .select(CAKE_DESIGN_ORDER.orderStatus)
            .from(CAKE_DESIGN_ORDER)
            .where(CAKE_DESIGN_ORDER.id.eq(orderId))
            .fetchFirst()
            ?: logger.error("주문 승인 실패: 주문 정보 없음")

        if (orderStatus != OrderStatus.NEW)
            logger.error("주문 승인 실패: 신규 주문이 아님")

        changeOrderStatus(orderId, OrderStatus.IN_PROGRESS)
    }

    private fun changeOrderStatus(orderId: Long, orderStatus: OrderStatus) {
        // 케이크 주문 승인 시 orderStatus NEW -> IN_PROGRESS 변경

        jpaQueryFactory
            .update(CAKE_DESIGN_ORDER)
            .set(CAKE_DESIGN_ORDER.orderStatus, orderStatus)
            .where(CAKE_DESIGN_ORDER.id.eq(orderId))
            .execute()
    }
}