package com.cake.customcake.adapter.out.persistence

import com.cake.customcake.adapter.out.persistence.mapper.CakeCustomOrderMapper
import com.cake.customcake.adapter.out.persistence.repository.CakeCustomOrderRepository
import com.cake.customcake.application.port.out.CakeCustomOrderPort
import com.cake.customcake.common.OrderStatus
import com.cake.customcake.domain.CakeCustomOrder
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import com.cake.customcake.adapter.out.persistence.entity.QCakeCustomOrderEntity.cakeCustomOrderEntity as CAKE_CUSTOM_ORDER

@Repository
class CakeCustomOrderPersistenceAdapter(
    private val cakeCustomOrderMapper: CakeCustomOrderMapper,
    private val cakeCustomOrderRepository: CakeCustomOrderRepository,
    private val jpaQueryFactory: JPAQueryFactory,
): CakeCustomOrderPort {
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
        TODO("Not yet implemented")
    }
}