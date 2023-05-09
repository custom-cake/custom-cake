package com.cake.customcake.adapter.out.persistence.entity

import java.time.LocalDateTime
import javax.persistence.*

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
    val isAuthenticated: Boolean,

    @Column(nullable = false)
    val lastConnDate: LocalDateTime
) : BaseEntity()