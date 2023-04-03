package com.cake.customcakebackend.adapter.out.persistence.mapper

interface Mapper<E, D> {
    fun toEntity(domain: D): E
    fun toDomain(entity: E): D
}