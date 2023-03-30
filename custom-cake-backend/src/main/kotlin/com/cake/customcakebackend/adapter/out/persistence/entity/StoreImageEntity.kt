package com.cake.customcakebackend.adapter.out.persistence.entity

import javax.persistence.*

@Table(name = "store_image")
@Entity
class StoreImageEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id")
    val store: StoreEntity,

    @Column(columnDefinition = "String", length = 255, nullable = false)
    val url: String,  // s3 image url

    @Column(columnDefinition = "TINYINT", nullable = false)
    val isThumbnail: Boolean

) : BaseEntity()