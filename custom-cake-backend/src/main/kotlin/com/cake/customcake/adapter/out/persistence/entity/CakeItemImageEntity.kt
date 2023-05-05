package com.cake.customcake.adapter.out.persistence.entity

import javax.persistence.*

@Table(name = "cake_item_image")
@Entity
class CakeItemImageEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    // @ManyToOne
    @Column(name = "cake_item_id", nullable = false)
    val cakeItemId: Long,

    @Column(columnDefinition = "String", length = 255, nullable = false)
    val url: String,  // s3 image url

    @Column(columnDefinition = "TINYINT", nullable = false)
    val isThumbnail: Boolean

) : BaseEntity()