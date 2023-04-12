package com.cake.customcakebackend.adapter.out.persistence.repository

import com.cake.customcakebackend.adapter.out.persistence.entity.StoreNotificationEntity
import org.springframework.data.jpa.repository.JpaRepository

interface StoreNotificationJpaRepository: JpaRepository<StoreNotificationEntity, Long> {
}