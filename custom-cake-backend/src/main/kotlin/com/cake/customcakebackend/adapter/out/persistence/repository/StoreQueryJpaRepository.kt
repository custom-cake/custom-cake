package com.cake.customcakebackend.adapter.out.persistence.repository

import com.cake.customcakebackend.adapter.out.persistence.entity.StoreEntity
import org.springframework.stereotype.Repository

@Repository
class StoreQueryJpaRepository {

    fun searchByOption(): List<StoreEntity> {
        return listOf()
    }
}