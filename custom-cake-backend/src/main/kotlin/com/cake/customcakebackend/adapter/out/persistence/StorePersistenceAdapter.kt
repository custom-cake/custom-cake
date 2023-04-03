package com.cake.customcakebackend.adapter.out.persistence

import com.cake.customcakebackend.adapter.out.persistence.mapper.StoreMapper
import com.cake.customcakebackend.adapter.out.persistence.repository.StoreJpaRepository
import com.cake.customcakebackend.application.port.out.StorePort
import com.cake.customcakebackend.domain.Store
import com.cake.customcakebackend.exception.StoreException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class StorePersistenceAdapter(
    private val storeMapper: StoreMapper,
    private val storeRepository: StoreJpaRepository
) : StorePort {
    override fun load(operatorId: Long): List<Store> {
        val storeEntity = storeRepository.findByIdOrNull(operatorId)

        return storeEntity
            ?.let { listOf(storeMapper.toDomain(it)) }
            ?: listOf()
    }

    override fun save(store: Store) {
        TODO("Not yet implemented")
    }

}