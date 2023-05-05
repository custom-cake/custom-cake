package com.cake.customcake.adapter.out.persistence.repository

import com.cake.customcake.adapter.out.persistence.entity.StoreGalleryEntity
import org.springframework.data.jpa.repository.JpaRepository

interface StoreGalleryJpaRepository: JpaRepository<StoreGalleryEntity, Long> {
}