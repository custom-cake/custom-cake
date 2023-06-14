package com.cake.customcake.adapter.out.persistence.repository

import com.cake.customcake.adapter.out.persistence.entity.CakeOptionEntity
import org.springframework.data.jpa.repository.JpaRepository


interface CakeOptionJpaRepository: JpaRepository<CakeOptionEntity, Long> {
}