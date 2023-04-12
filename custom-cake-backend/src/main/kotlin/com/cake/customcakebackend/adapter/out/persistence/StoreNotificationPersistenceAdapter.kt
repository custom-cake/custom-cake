package com.cake.customcakebackend.adapter.out.persistence

import com.cake.customcakebackend.adapter.out.persistence.mapper.StoreNotificationMapper
import com.cake.customcakebackend.adapter.out.persistence.entity.QStoreNotificationEntity.storeNotificationEntity as notification
import com.cake.customcakebackend.adapter.out.persistence.repository.StoreNotificationJpaRepository
import com.cake.customcakebackend.application.port.out.StoreNotificationPort
import com.cake.customcakebackend.domain.StoreNotification
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import javax.persistence.EntityNotFoundException

@Repository
class StoreNotificationPersistenceAdapter(
    private val storeNotificationMapper: StoreNotificationMapper,
    private val storeNotificationJpaRepository: StoreNotificationJpaRepository,
    private val jpaQueryFactory: JPAQueryFactory
): StoreNotificationPort {
    override fun loadNotificationList(storeId: Long): List<StoreNotification> =
        jpaQueryFactory
            .selectFrom(notification)
            .where(notification.storeId.eq(storeId))
            .fetch()
            .map { storeNotificationMapper.toDomain(it) }



    override fun loadNotification(notificationId: Long): StoreNotification =
        storeNotificationJpaRepository.findByIdOrNull(notificationId)
            ?. let { storeNotificationMapper.toDomain(it) }
            ?: throw EntityNotFoundException("StoreNotification id=$notificationId not found.")
}