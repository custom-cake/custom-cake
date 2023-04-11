package com.cake.customcakebackend.adapter.out.persistence.repository

import com.cake.customcakebackend.adapter.out.persistence.entity.CakeOptionEntity
import org.springframework.data.jpa.repository.JpaRepository


interface CakeOptionJpaRepository: JpaRepository<CakeOptionEntity, Long> {
}