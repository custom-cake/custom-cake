package com.cake.customcake.adapter.out.persistence

import com.cake.customcake.adapter.`in`.web.dto.request.StoreOptionSearchRequest
import com.cake.customcake.adapter.out.persistence.entity.StoreEntity
import com.cake.customcake.adapter.out.persistence.mapper.StoreMapper
import com.cake.customcake.adapter.out.persistence.repository.StoreJpaRepository
import com.cake.customcake.adapter.out.persistence.repository.StoreQueryJpaRepository
import com.cake.customcake.application.port.out.LoadStoresByNamePort
import com.cake.customcake.application.port.out.LoadStoresByOptionPort
import com.cake.customcake.application.port.out.StorePort
import com.cake.customcake.domain.Store
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import javax.persistence.EntityNotFoundException
import com.cake.customcake.adapter.out.persistence.entity.QStoreEntity.storeEntity as store

@Repository
class StorePersistenceAdapter(
    private val storeMapper: StoreMapper,
    private val storeJpaRepository: StoreJpaRepository,
    private val storeQueryJpaRepository: StoreQueryJpaRepository,
    private val jpaQueryFactory: JPAQueryFactory
) : StorePort, LoadStoresByNamePort, LoadStoresByOptionPort {
    // StorePort
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

    // LoadStoresByNameUserPort
    override fun loadByName(query: String): List<Store> {
        return storeJpaRepository.findByNameContaining(query)
            .map(storeMapper::toDomain)
    }

    // LoadStoresByOptionUserPort
    override fun loadByOption(request: StoreOptionSearchRequest): List<Store> {
        return storeQueryJpaRepository.searchByOption(request)
            .map(storeMapper::toDomain)
    }
}