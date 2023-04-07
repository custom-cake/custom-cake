package com.cake.customcakebackend.adapter.out.persistence

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
        TODO()
    }

    override fun loadList(storeId: Long): List<CakeItem> {
//        val cakeItemEntityList = jpaQueryFactory.select()

//        return cakeItemEntityList.map { cakeItemMapper.toDomain(it) }
        return listOf()
    }

    override fun save(): Long {
        TODO("Not yet implemented")
    }

    override fun modify(cakeItemId: Long): Long {
        TODO("Not yet implemented")
    }

    override fun delete(cakeItemId: Long) {
        TODO("Not yet implemented")
    }
}