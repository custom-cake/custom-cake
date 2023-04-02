package com.cake.customcakebackend.adapter.out.persistence.repository

import com.cake.customcakebackend.adapter.out.persistence.entity.StoreEntity
import org.springframework.data.jpa.repository.JpaRepository

interface StoreJpaRepository : JpaRepository<StoreEntity, Long> {
}