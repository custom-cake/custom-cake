package com.cake.customcakebackend.adapter.out.persistence

import com.cake.customcakebackend.adapter.out.persistence.mapper.StoreMapper
import com.cake.customcakebackend.adapter.out.persistence.repository.StoreJpaRepository
import com.cake.customcakebackend.application.port.out.LoadStoresByNameUserPort
import com.cake.customcakebackend.domain.Store
import org.springframework.stereotype.Component

@Component
class StorePersistentAdapter(
    private val storeJpaRepository: StoreJpaRepository,
    private val storeMapper: StoreMapper
) : LoadStoresByNameUserPort {

    override fun loadByName(query: String): List<Store> {
        return storeJpaRepository.findByNameContaining(query)
            .map(storeMapper::toDomain)
    }
}