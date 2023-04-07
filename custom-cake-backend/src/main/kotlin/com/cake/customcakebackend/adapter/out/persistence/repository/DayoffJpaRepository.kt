package com.cake.customcakebackend.adapter.out.persistence.repository

import com.cake.customcakebackend.adapter.out.persistence.entity.DayoffEntity
import org.springframework.data.jpa.repository.JpaRepository

interface DayoffJpaRepository : JpaRepository<DayoffEntity, Long> {
}