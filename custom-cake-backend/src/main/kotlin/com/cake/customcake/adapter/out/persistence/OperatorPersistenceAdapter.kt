package com.cake.customcake.adapter.out.persistence

import com.cake.customcake.adapter.out.persistence.entity.QStoreEntity.storeEntity as STORE
import com.cake.customcake.adapter.out.persistence.mapper.OperatorMapper
import com.cake.customcake.application.port.out.OperatorPort
import com.cake.customcake.domain.Operator
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import com.cake.customcake.adapter.out.persistence.entity.QOperatorEntity.operatorEntity as OPERATOR

@Repository
class OperatorPersistenceAdapter(
    private val operatorMapper: OperatorMapper,
    private val jpaQueryFactory: JPAQueryFactory
): OperatorPort {
    override fun loadByEmailAndPassword(email: String, password: String): Pair<Operator?, Long?> {
        val operatorEntity = jpaQueryFactory
            .selectFrom(OPERATOR)
            .where(
                OPERATOR.email.eq(email),
                OPERATOR.password.eq(password)
            ).fetchOne()

        operatorEntity
            ?. let {
                // store id
                val storeId = jpaQueryFactory.select(STORE.id).from(STORE).where(STORE.operatorId.eq(it.id)).fetchOne()
                return (operatorMapper.toDomain(it) to storeId)
            }
            ?: return (null to null)
    }

    override fun save(): Long {
        TODO("Not yet implemented")
    }
}