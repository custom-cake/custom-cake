package com.cake.customcake.adapter.out.persistence.repository

import com.cake.customcake.adapter.out.persistence.entity.CakeItemImageEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CakeItemImageRepository : JpaRepository<CakeItemImageEntity, Long> {

    fun findByCakeItemIdAndIsThumbnailIsTrue(cakeItemId: Long): CakeItemImageEntity
}