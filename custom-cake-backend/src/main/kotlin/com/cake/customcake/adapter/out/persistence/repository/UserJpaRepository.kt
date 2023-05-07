package com.cake.customcake.adapter.out.persistence.repository

import com.cake.customcake.adapter.out.persistence.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepository : JpaRepository<UserEntity, Long> {
}