package com.cake.customcake.adapter.out.persistence.repository

import com.cake.customcake.adapter.out.persistence.entity.OperatorEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OperatorJpaRepository : JpaRepository<OperatorEntity, Long> {
}