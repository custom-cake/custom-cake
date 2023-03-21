package com.cake.customcakebackend.infrastructure.persistence.database.entity

import lombok.Getter
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.ZonedDateTime
import javax.persistence.*

@Getter
@EntityListeners(AuditingEntityListener::class)
@MappedSuperclass
abstract class BaseEntity (
    @CreatedDate
    @Column(nullable = false, updatable = false)
    val createdAt: ZonedDateTime = ZonedDateTime.now(),

    @LastModifiedDate
    @Column(nullable = false, updatable = true)
    var modifiedAt: ZonedDateTime = ZonedDateTime.now()
)
