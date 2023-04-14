package com.cake.customcakebackend.adapter.out.persistence.repository

import com.cake.customcakebackend.adapter.out.persistence.entity.OptionByCakeEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OptionByCakeJpaRepository: JpaRepository<OptionByCakeEntity, Long> {
}