package com.cake.customcakebackend.adapter.out.persistence.mapper

import com.cake.customcakebackend.adapter.out.persistence.entity.OperatorEntity
import com.cake.customcakebackend.domain.Operator
import org.springframework.stereotype.Component

@Component
class OperatorMapper : Mapper<OperatorEntity, Operator>  {
    override fun toEntity(domain: Operator): OperatorEntity =
        OperatorEntity(
            id = domain.id,
            name = domain.name,
            password = domain.password,
            email = domain.email,
            phone = domain.phone,
            isAuthenticated = domain.isAuthenticated,
            lastConnDate = domain.lastConnDate
        )

    override fun toDomain(entity: OperatorEntity): Operator =
        Operator(
            id = entity.id,
            name = entity.name,
            password = entity.password,
            email = entity.email,
            phone = entity.phone,
            isAuthenticated = entity.isAuthenticated,
            lastConnDate = entity.lastConnDate,
            createdAt = entity.createdAt,
            modifiedAt = entity.modifiedAt
        )
}