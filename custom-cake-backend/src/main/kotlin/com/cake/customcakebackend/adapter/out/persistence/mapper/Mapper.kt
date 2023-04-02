package com.cake.customcakebackend.adapter.out.persistence.mapper

import org.springframework.stereotype.Component

interface Mapper<E, D> {
    fun toEntity(domain: D): E
    fun toDomain(entity: E): D
}