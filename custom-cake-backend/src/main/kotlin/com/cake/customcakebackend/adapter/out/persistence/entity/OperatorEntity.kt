package com.cake.customcakebackend.adapter.out.persistence.entity

import lombok.Getter
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Getter
@Table(name = "operator")
@Entity
class OperatorEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(columnDefinition = "String", length = 100, nullable = false, unique = true)
    val email: String,

    @Column(columnDefinition = "String", length = 255, nullable = false)
    val password: String,

    @Column(columnDefinition = "String", length = 50, nullable = false)
    val name: String,

    @Column(columnDefinition = "String", length = 20, nullable = false)
    val phone: String,

    @Column(columnDefinition = "TINYINT", nullable = false)
    val isAuthentication: Boolean,

    @Column(nullable = false)
    val lastConnDate: LocalDateTime
) : BaseEntity()