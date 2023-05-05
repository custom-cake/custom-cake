package com.cake.customcake.adapter.out.persistence.repository

import com.cake.customcake.adapter.out.persistence.entity.StoreNotificationEntity
import org.springframework.data.jpa.repository.JpaRepository

interface StoreNotificationJpaRepository: JpaRepository<StoreNotificationEntity, Long> {
}