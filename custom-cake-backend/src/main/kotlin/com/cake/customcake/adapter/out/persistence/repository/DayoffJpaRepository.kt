package com.cake.customcake.adapter.out.persistence.repository

import com.cake.customcake.adapter.out.persistence.entity.DayoffEntity
import org.springframework.data.jpa.repository.JpaRepository

interface DayoffJpaRepository : JpaRepository<DayoffEntity, Long> {
}