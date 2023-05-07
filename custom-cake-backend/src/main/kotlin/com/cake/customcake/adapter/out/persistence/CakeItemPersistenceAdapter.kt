package com.cake.customcake.adapter.out.persistence

import com.cake.customcake.adapter.out.persistence.mapper.CakeItemMapper
import com.cake.customcake.adapter.out.persistence.repository.CakeItemJpaRepository
import com.cake.customcake.application.port.out.CakeItemPort
import com.cake.customcake.domain.CakeItem
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import javax.persistence.EntityNotFoundException
import com.cake.customcake.adapter.out.persistence.entity.QCakeItemEntity.cakeItemEntity as CAKEITEM

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

    override fun loadCakeItemName(cakeItemId: Long): String =
        jpaQueryFactory
            .select(CAKEITEM.name)
            .from(CAKEITEM)
            .where(CAKEITEM.id.eq(cakeItemId))
            .fetchFirst()
            ?.toString()
            ?: throw EntityNotFoundException("CakeItem id=$cakeItemId not found.")

    override fun loadCakeItemNameAndImage(cakeItemId: Long): Pair<String, String> =
         jpaQueryFactory
            .select(CAKEITEM.name, CAKEITEM.thumbnailImageUrl)
            .from(CAKEITEM)
            .where(CAKEITEM.id.eq(cakeItemId))
            .fetch()
            .map { it.get(0, String::class.java)!! to it.get(1, String::class.java)!! }
            .firstOrNull()
            ?: throw EntityNotFoundException("CakeItem id=$cakeItemId not found.")

    override fun loadList(storeId: Long): List<CakeItem> {
        val cakeItemEntities = jpaQueryFactory
            .selectFrom(CAKEITEM)
            .where(
                CAKEITEM.storeId.eq(storeId),
                CAKEITEM.isDeleted.isFalse)
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
            .update(CAKEITEM)
            .set(CAKEITEM.isDeleted, true)
            .where(CAKEITEM.id.eq(cakeItemId))
            .execute()
    }
}