package com.cake.customcakebackend.adapter.out.persistence

import com.cake.customcakebackend.adapter.out.persistence.entity.StoreEntity
import com.cake.customcakebackend.adapter.out.persistence.mapper.StoreMapper
import com.cake.customcakebackend.adapter.out.persistence.repository.StoreJpaRepository
import com.cake.customcakebackend.application.port.out.LoadStoresByNameUserPort
import com.cake.customcakebackend.application.port.out.StorePort
import com.cake.customcakebackend.domain.Store
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import javax.persistence.EntityNotFoundException
import com.cake.customcakebackend.adapter.out.persistence.entity.QDayoffEntity.dayoffEntity as dayoff
import com.cake.customcakebackend.adapter.out.persistence.entity.QStoreEntity.storeEntity as store

@Repository
class StorePersistenceAdapter(
    private val storeMapper: StoreMapper,
    private val storeJpaRepository: StoreJpaRepository,
    private val jpaQueryFactory: JPAQueryFactory
) : StorePort, LoadStoresByNameUserPort {
    override fun loadByOperatorId(operatorId: Long): List<Store> {
        val storeEntity = jpaQueryFactory
            .selectFrom(store)
            .where(store.operatorId.eq(operatorId))
            .fetchOne()

        return storeEntity
            ?.let { listOf(storeMapper.toDomain(it)) }
            ?: listOf()
    }

    override fun loadByStoreId(storeId: Long): Store {
        val storeEntity = storeJpaRepository.findByIdOrNull(storeId)
            ?: throw EntityNotFoundException("Store id=$storeId not found.")

        return storeMapper.toDomain(storeEntity)
    }

    override fun exist(operatorId: Long): Boolean =
        storeJpaRepository.existsById(operatorId)

    override fun validateStore(storeId: Long, operatorId: Long): Boolean {
        val storeEntity: StoreEntity? = jpaQueryFactory
            .selectFrom(store)
            .where(
                store.id.eq(storeId),
                store.operatorId.eq(operatorId)
            )
            .fetchOne()
        return storeEntity ?. let { true } ?: false
    }

    override fun save(store: Store): Long {
        val storeEntity = storeMapper.toEntity(store)
        return storeJpaRepository.save(storeEntity).id
    }

    override fun loadByName(query: String): List<Store> {
        return storeJpaRepository.findByNameContaining(query)
            .map(storeMapper::toDomain)
    }

}