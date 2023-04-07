package com.cake.customcakebackend.adapter.out.persistence.entity

import org.springframework.data.jpa.domain.support.AuditingEntityListener
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
interface CakeOptionEntity {
}