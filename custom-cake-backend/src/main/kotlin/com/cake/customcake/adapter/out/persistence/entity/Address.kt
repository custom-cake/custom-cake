package com.cake.customcake.adapter.out.persistence.entity

import com.cake.customcake.common.TempStoreRegion
import com.cake.customcake.common.converter.TempStoreRegionConverter
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Embeddable


@Embeddable
class Address(
    @Column(columnDefinition = "String", length = 5, nullable = false)
    val zipCode: String,
    @Column(columnDefinition = "String", length = 100, nullable = false)
    val baseAddress: String,
    @Column(columnDefinition = "String", length = 100)
    val detailAddress: String? = "",
    @Convert(converter = TempStoreRegionConverter::class)
    @Column(columnDefinition = "String", length = 10, nullable = false)
    val region: TempStoreRegion
)
