package com.cake.customcake.adapter.out.persistence.repository

import com.cake.customcake.adapter.out.persistence.entity.StoreEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface StoreJpaRepository : JpaRepository <StoreEntity, Long> {
    fun findByNameContaining(@Param("q") query: String): List<StoreEntity>
}