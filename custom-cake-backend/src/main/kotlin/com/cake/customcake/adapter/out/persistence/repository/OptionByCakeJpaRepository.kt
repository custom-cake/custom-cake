package com.cake.customcake.adapter.out.persistence.repository

import com.cake.customcake.adapter.out.persistence.entity.OptionByCakeEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OptionByCakeJpaRepository: JpaRepository<OptionByCakeEntity, Long> {
}