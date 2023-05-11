package com.cake.customcake.adapter.out.persistence.repository

import com.cake.customcake.adapter.out.persistence.entity.CakeCustomOrderSheetEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CakeCustomOrderSheetRepository : JpaRepository<CakeCustomOrderSheetEntity, Long> {
}