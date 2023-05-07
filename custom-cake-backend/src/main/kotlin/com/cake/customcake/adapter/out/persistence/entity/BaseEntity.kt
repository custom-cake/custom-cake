package com.cake.customcake.adapter.out.persistence.entity

import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class BaseEntity (
    @Column
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column
    var modifiedAt: LocalDateTime = LocalDateTime.now()
) {
    @PrePersist
    fun prePersist() {
        modifiedAt = LocalDateTime.now()
    }

    @PreUpdate
    fun preUpdate() {
        modifiedAt = LocalDateTime.now()
    }
}
