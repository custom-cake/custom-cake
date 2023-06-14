package com.cake.customcake.adapter.out.persistence.repository

import com.cake.customcake.adapter.out.persistence.entity.CakeItemEntity
import org.springframework.data.jpa.repository.JpaRepository


interface CakeItemJpaRepository: JpaRepository<CakeItemEntity, Long> {
}