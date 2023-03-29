package com.cake.customcakebackend.adapter.out.persistence.entity

import lombok.Getter
import javax.persistence.*

@Getter
@Table(name = "cake_item_image")
@Entity
class CakeItemImageEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cake_item_id")
    val cakeItem: CakeItemEntity,

    @Column(columnDefinition = "String", length = 255, nullable = false)
    val url: String,  // s3 image url

    @Column(columnDefinition = "TINYINT", nullable = false)
    val isThumbnail: Boolean

) : BaseEntity()