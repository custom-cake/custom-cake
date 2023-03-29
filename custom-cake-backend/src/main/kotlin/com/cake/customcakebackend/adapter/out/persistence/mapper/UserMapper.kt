package com.cake.customcakebackend.adapter.out.persistence.mapper

import com.cake.customcakebackend.adapter.out.persistence.entity.UserEntity
import com.cake.customcakebackend.domain.User

class UserMapper {

    fun toDomain(userEntity: UserEntity)
        = User(userEntity.id, userEntity.name)
    fun toEntity(user: User): UserEntity
        = UserEntity(user.id, user.name)
}