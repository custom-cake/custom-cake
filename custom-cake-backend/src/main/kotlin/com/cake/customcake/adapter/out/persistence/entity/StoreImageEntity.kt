package com.cake.customcake.adapter.out.persistence.entity

import javax.persistence.*

@Table(name = "store_image")
@Entity
class StoreImageEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    // @ManyToOne
    @Column(name = "store_id", nullable = false)
    val storeId: Long,

    @Column(columnDefinition = "String", length = 255, nullable = false)
    val url: String,  // s3 image url

    @Column(columnDefinition = "TINYINT", nullable = false)
    val isThumbnail: Boolean

) : BaseEntity()