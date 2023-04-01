package com.cake.customcakebackend.adapter.out.persistence.repository

import com.cake.customcakebackend.adapter.out.persistence.entity.StoreEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StoreJpaRepository : JpaRepository <StoreEntity, Long> {
    fun findByNameContaining(query: String): List<StoreEntity>
}