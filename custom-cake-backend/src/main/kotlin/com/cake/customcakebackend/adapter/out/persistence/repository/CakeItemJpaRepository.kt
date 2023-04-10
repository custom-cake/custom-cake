package com.cake.customcakebackend.adapter.out.persistence.repository

import com.cake.customcakebackend.adapter.out.persistence.entity.CakeItemEntity
import org.springframework.data.jpa.repository.JpaRepository


interface CakeItemJpaRepository: JpaRepository<CakeItemEntity, Long> {
}