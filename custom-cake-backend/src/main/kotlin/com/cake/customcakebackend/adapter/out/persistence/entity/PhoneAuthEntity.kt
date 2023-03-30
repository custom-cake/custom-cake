package com.cake.customcakebackend.adapter.out.persistence.entity

import javax.persistence.*

@Table(name = "phone_auth")
@Entity
class PhoneAuthEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(columnDefinition = "String", length = 20, nullable = false)
    val phone: String,

    @Column(columnDefinition = "String", length = 255, nullable = false)
    val authToken: String

) : BaseEntity()