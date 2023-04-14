package com.cake.customcakebackend.adapter.out.persistence

import com.cake.customcakebackend.adapter.out.persistence.entity.QUserEntity.userEntity as USER
import com.cake.customcakebackend.adapter.out.persistence.mapper.UserMapper
import com.cake.customcakebackend.adapter.out.persistence.repository.UserJpaRepository
import com.cake.customcakebackend.application.port.out.LoadUserPort
import com.cake.customcakebackend.application.port.out.SaveUserPort
import com.cake.customcakebackend.domain.User
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import javax.persistence.EntityNotFoundException

@Repository
class UserPersistenceAdapter(
    private val userMapper: UserMapper,
    private val userJpaRepository: UserJpaRepository,
    private val jpaQueryFactory: JPAQueryFactory
) : LoadUserPort, SaveUserPort {
    override fun load(id: Long): User {
        TODO("Change entity to domain and return domain.")
        // toDomain()
    }

    override fun loadNickName(userId: Long): String {
        val userNickName = jpaQueryFactory
            .select(USER.nickname)
            .from(USER)
            .where(USER.id.eq(userId))
            .fetchOne()

        return userNickName
            ?: throw EntityNotFoundException("User id=$userId not found.")
    }

    override fun save(user: User) {
        TODO("Change domain to entity and save entity.")
//        val userEntity = userMapper.toEntity(user)
//        userJpaRepository.save(userEntity)
    }
}