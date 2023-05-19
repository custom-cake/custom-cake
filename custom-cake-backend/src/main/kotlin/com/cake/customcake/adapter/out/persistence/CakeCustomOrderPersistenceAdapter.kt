package com.cake.customcake.adapter.out.persistence

import com.cake.customcake.adapter.out.persistence.entity.QCakeCustomOrderEntity
import com.cake.customcake.adapter.out.persistence.entity.QCakeCustomOrderEntity.cakeCustomOrderEntity as CUSTOM_ORDER
import com.cake.customcake.adapter.out.persistence.entity.QCakeDesignOrderEntity
import com.cake.customcake.adapter.out.persistence.mapper.CakeCustomOrderMapper
import com.cake.customcake.adapter.out.persistence.repository.CakeCustomOrderRepository
import com.cake.customcake.application.port.out.CakeCustomOrderPort
import com.cake.customcake.common.OrderStatus
import com.cake.customcake.domain.CakeCustomOrder
import com.querydsl.jpa.impl.JPAQueryFactory
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository
import com.cake.customcake.adapter.out.persistence.entity.QCakeCustomOrderEntity.cakeCustomOrderEntity as CAKE_CUSTOM_ORDER

@Repository
class CakeCustomOrderPersistenceAdapter(
    private val cakeCustomOrderMapper: CakeCustomOrderMapper,
    private val cakeCustomOrderRepository: CakeCustomOrderRepository,
    private val jpaQueryFactory: JPAQueryFactory,
): CakeCustomOrderPort {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)
    override fun save(cakeCustomOrder: CakeCustomOrder) {
        cakeCustomOrderRepository.save(cakeCustomOrderMapper.toEntity(cakeCustomOrder))
    }

    override fun loadListByUserId(userId: Long): List<CakeCustomOrder> =
        jpaQueryFactory
            .selectFrom(CAKE_CUSTOM_ORDER)
            .where(CAKE_CUSTOM_ORDER.userId.eq(userId))
            .fetch()
            .map { cakeCustomOrderMapper.toDomain(it) }

    override fun loadListByStoreIdAndOrderStatus(storeId: Long, orderStatus: OrderStatus): List<CakeCustomOrder> =
        // 매장의 커스텀 케이크 주문 내역
        jpaQueryFactory
            .selectFrom(CAKE_CUSTOM_ORDER)
            .where(CAKE_CUSTOM_ORDER.storeId.eq(storeId),
                CAKE_CUSTOM_ORDER.orderStatus.eq(orderStatus))
            .fetch()
            .map { cakeCustomOrderMapper.toDomain(it) }

    override fun approveCakeOrder(orderId: Long) {
        // 케이크 주문 승인 시 orderStatus NEW -> IN_PROGRESS 변경
        val orderStatus = jpaQueryFactory
            .select(CUSTOM_ORDER.orderStatus)
            .from(CUSTOM_ORDER)
            .where(CUSTOM_ORDER.id.eq(orderId))
            .fetchFirst()
            ?: logger.error("주문 승인 실패: 주문 정보 없음")

        // check orderStatus == NEW
        if (orderStatus != OrderStatus.NEW)
            logger.error("주문 승인 실패: 신규 주문이 아님")

        changeOrderStatus(orderId, OrderStatus.IN_PROGRESS)
    }

    private fun changeOrderStatus(orderId: Long, orderStatus: OrderStatus) {
        // 케이크 주문 승인 시 orderStatus NEW -> IN_PROGRESS 변경

        jpaQueryFactory
            .update(CUSTOM_ORDER)
            .set(CUSTOM_ORDER.orderStatus, orderStatus)
            .where(CUSTOM_ORDER.id.eq(orderId))
            .execute()
    }
}