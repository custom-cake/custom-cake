package com.cake.customcakebackend.adapter.out.persistence

import com.cake.customcakebackend.adapter.out.persistence.mapper.OperatorMapper
import com.cake.customcakebackend.application.port.out.OperatorPort
import com.cake.customcakebackend.domain.Operator
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import com.cake.customcakebackend.adapter.out.persistence.entity.QOperatorEntity.operatorEntity as OPERATOR

@Repository
class OperatorPersistenceAdapter(
    private val operatorMapper: OperatorMapper,
    private val jpaQueryFactory: JPAQueryFactory
): OperatorPort {
    override fun loadByEmailAndPassword(email: String, password: String): List<Operator> =
        jpaQueryFactory
            .selectFrom(OPERATOR)
            .where(
                OPERATOR.email.eq(email),
                OPERATOR.password.eq(password)
            ).fetch()
            .map { operatorMapper.toDomain(it) }

    override fun save(): Long {
        TODO("Not yet implemented")
    }
}