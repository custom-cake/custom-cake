package com.cake.customcake.adapter.out.persistence.repository

import com.cake.customcake.adapter.out.persistence.entity.CakeCustomOrderEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CakeCustomOrderRepository : JpaRepository<CakeCustomOrderEntity, Long> {
}