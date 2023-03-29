package com.cake.customcakebackend.adapter.out.persistence.entity

import javax.persistence.Column
import javax.persistence.Embeddable


@Embeddable
class Address(
    @Column
    val zipCode: String,
    @Column
    val baseAddress: String,
    @Column
    val detailAddress: String,
)
