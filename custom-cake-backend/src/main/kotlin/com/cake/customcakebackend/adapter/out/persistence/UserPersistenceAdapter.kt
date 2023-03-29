package com.cake.customcakebackend.adapter.out.persistence

import com.cake.customcakebackend.adapter.out.persistence.entity.UserEntity
import com.cake.customcakebackend.adapter.out.persistence.mapper.UserMapper
import com.cake.customcakebackend.adapter.out.persistence.repository.UserJpaRepository
import com.cake.customcakebackend.application.port.out.LoadUserPort
import com.cake.customcakebackend.application.port.out.SaveUserPort
import com.cake.customcakebackend.domain.User

class UserPersistenceAdapter(
    private val userMapper: UserMapper,
    private val userJpaRepository: UserJpaRepository
) : LoadUserPort, SaveUserPort {
    override fun load(id: Long): User {
        TODO("Change entity to domain and return domain.")
    }

    override fun save(user: User) {
        TODO("Change domain to entity and save entity.")
//        val userEntity = userMapper.toEntity(user)
//        userJpaRepository.save(userEntity)
    }
}