package com.cake.customcakebackend.adapter.out.persistence.mapper

import com.cake.customcakebackend.adapter.out.persistence.entity.OperatorEntity
import com.cake.customcakebackend.domain.Operator

class OperatorMapper : Mapper<OperatorEntity, Operator>  {
    override fun toEntity(domain: Operator): OperatorEntity {
        TODO("Not yet implemented")
    }

    override fun toDomain(entity: OperatorEntity): Operator {
        TODO("Not yet implemented")
    }
}