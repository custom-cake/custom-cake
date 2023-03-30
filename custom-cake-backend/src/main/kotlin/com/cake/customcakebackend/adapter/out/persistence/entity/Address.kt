package com.cake.customcakebackend.adapter.out.persistence.entity

import javax.persistence.Column
import javax.persistence.Embeddable


@Embeddable
class Address(
    @Column(columnDefinition = "String", length = 5, nullable = false)
    val zipCode: String,
    @Column(columnDefinition = "String", length = 100, nullable = false)
    val baseAddress: String,
    @Column(columnDefinition = "String", length = 100)
    val detailAddress: String? = "",
)
