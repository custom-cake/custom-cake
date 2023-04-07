package com.cake.customcakebackend.adapter.out.persistence.mapper

import com.cake.customcakebackend.adapter.out.persistence.entity.CakeOptionEntity
import com.cake.customcakebackend.domain.CakeOption
import org.springframework.stereotype.Component

@Component
class CakeOptionMapper : Mapper<CakeOptionEntity, CakeOption> {
    override fun toEntity(domain: CakeOption): CakeOptionEntity {
        TODO("Not yet implemented")
    }

    override fun toDomain(entity: CakeOptionEntity): CakeOption {
        TODO("Not yet implemented")
    }
}