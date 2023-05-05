package com.cake.customcake.adapter.out.persistence

import com.cake.customcake.adapter.out.persistence.entity.StoreGalleryEntity
import com.cake.customcake.adapter.out.persistence.mapper.StoreGalleryMapper
import com.cake.customcake.adapter.out.persistence.repository.StoreGalleryJpaRepository
import com.cake.customcake.adapter.out.persistence.entity.QStoreGalleryEntity.storeGalleryEntity as STORE_GALLERY
import com.cake.customcake.application.port.out.StoreGalleryPort
import com.cake.customcake.domain.StoreGallery
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import javax.persistence.EntityNotFoundException

@Repository
class StoreGalleryPersistenceAdapter(
    private val storeGalleryMapper: StoreGalleryMapper,
    private val jpaQueryFactory: JPAQueryFactory,
    private val storeGalleryJpaRepository: StoreGalleryJpaRepository
) : StoreGalleryPort {
    override fun load(storeId: Long): StoreGallery {
        // 매장 등록 시, store gallery 생성
        val storeGalleryEntity = (jpaQueryFactory
            .selectFrom(STORE_GALLERY)
            .where(STORE_GALLERY.storeId.eq(storeId))
            .fetchFirst()
            ?: throw EntityNotFoundException("StoreGallery store id=$storeId not found."))

        return storeGalleryMapper.toDomain(storeGalleryEntity)
    }

    override fun save(storeId: Long) {
        storeGalleryJpaRepository.save(
            StoreGalleryEntity(
                storeId = storeId,
                imageUrlList = listOf()
            )
        )
    }
}