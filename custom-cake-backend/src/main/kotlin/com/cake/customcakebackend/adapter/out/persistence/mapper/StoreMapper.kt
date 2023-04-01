package com.cake.customcakebackend.adapter.out.persistence.mapper

import com.cake.customcakebackend.adapter.out.persistence.entity.StoreEntity
import com.cake.customcakebackend.domain.Store
import org.springframework.stereotype.Component

@Component
class StoreMapper : Mapper<StoreEntity, Store> {

    override fun toEntity(domain: Store): StoreEntity {
        return // Todo : mapping
    }

    override fun toDomain(entity: StoreEntity): Store {
        TODO("Not yet implemented")
    }
}