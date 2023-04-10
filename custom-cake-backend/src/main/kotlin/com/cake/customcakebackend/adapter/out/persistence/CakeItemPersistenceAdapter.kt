package com.cake.customcakebackend.adapter.out.persistence

import com.cake.customcakebackend.adapter.out.persistence.entity.QCakeItemEntity.cakeItemEntity
import com.cake.customcakebackend.adapter.out.persistence.entity.QCakeItemImageEntity.cakeItemImageEntity
import com.cake.customcakebackend.adapter.out.persistence.entity.QCakeOption1Entity
import com.cake.customcakebackend.adapter.out.persistence.entity.QCakeOption3Entity
import com.cake.customcakebackend.adapter.out.persistence.mapper.CakeItemMapper
import com.cake.customcakebackend.adapter.out.persistence.repository.CakeItemJpaRepository
import com.cake.customcakebackend.application.port.out.CakeItemPort
import com.cake.customcakebackend.domain.CakeItem
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import javax.persistence.EntityNotFoundException

@Repository
class CakeItemPersistenceAdapter(
    private val cakeItemMapper: CakeItemMapper,
    private val jpaQueryFactory: JPAQueryFactory,
    private val cakeItemJpaRepository: CakeItemJpaRepository
) : CakeItemPort {
    override fun loadInfo(cakeItemId: Long): CakeItem {
        val cakeItemEntity = cakeItemJpaRepository.findByIdOrNull(cakeItemId)
            ?: throw EntityNotFoundException("CakeItem id=$cakeItemId not found.")

        return cakeItemMapper.toDomain(cakeItemEntity)
    }

    override fun loadList(storeId: Long): List<CakeItem> {
        val cakeItemEntities = jpaQueryFactory
            .selectFrom(cakeItemEntity)
            .where(
                cakeItemEntity.storeId.eq(storeId),
                cakeItemEntity.isDeleted.isFalse)
            .fetch()

        return cakeItemEntities.map { cakeItemMapper.toDomain(it) }
    }

    override fun save(): Long {
        TODO("Not yet implemented")
    }

    override fun modify(cakeItemId: Long): Long {
        TODO("Not yet implemented")
    }

    override fun delete(cakeItemId: Long) {
        // cake_item isDeleted 수정
        jpaQueryFactory
            .update(cakeItemEntity)
            .set(cakeItemEntity.isDeleted, true)
            .where(cakeItemEntity.id.eq(cakeItemId))
            .execute()
    }
}