package com.cake.customcakebackend.adapter.out.persistence.repository

import com.cake.customcakebackend.adapter.out.persistence.entity.StoreGalleryEntity
import org.springframework.data.jpa.repository.JpaRepository

interface StoreGalleryJpaRepository: JpaRepository<StoreGalleryEntity, Long> {
}