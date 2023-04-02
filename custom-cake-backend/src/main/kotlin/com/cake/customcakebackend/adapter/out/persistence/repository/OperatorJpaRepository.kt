package com.cake.customcakebackend.adapter.out.persistence.repository

import com.cake.customcakebackend.adapter.out.persistence.entity.OperatorEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OperatorJpaRepository : JpaRepository<OperatorEntity, Long> {
}