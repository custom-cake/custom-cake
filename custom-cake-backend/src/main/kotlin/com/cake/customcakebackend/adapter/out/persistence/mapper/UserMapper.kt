package com.cake.customcakebackend.adapter.out.persistence.mapper

import com.cake.customcakebackend.adapter.out.persistence.entity.UserEntity
import com.cake.customcakebackend.domain.User

class UserMapper : Mapper<UserEntity, User> {
    override fun toEntity(domain: User): UserEntity =
        UserEntity(
            id = domain.id,
            name = domain.name,
            phone = domain.phone,
            nickname = domain.nickname,
            socialType = domain.socialType,
            socialAccountId = domain.socialAccountId,
            isAuthenticated = domain.isAuthenticated,
            lastConnDatetime = domain.lastConnDatetime
        )

    override fun toDomain(entity: UserEntity): User =
        User(
            id = entity.id,
            name = entity.name,
            phone = entity.phone,
            nickname = entity.nickname,
            socialType = entity.socialType,
            socialAccountId = entity.socialAccountId,
            isAuthenticated = entity.isAuthenticated,
            lastConnDatetime = entity.lastConnDatetime,
            createdAt = entity.createdAt,
            modifiedAt = entity.modifiedAt
        )
}