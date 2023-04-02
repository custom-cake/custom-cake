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
    override fun load(operatorId: Long): Store {
        val storeEntity = storeRepository.findByIdOrNull(operatorId)
            ?: throw StoreException.NotFound("등록한 매장이 없습니다.")

        return storeMapper.toDomain(storeEntity)
    }

    override fun save(store: Store) {
        TODO("Not yet implemented")
    }

}