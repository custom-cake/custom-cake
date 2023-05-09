package com.cake.customcake.adapter.out.persistence.entity

import com.cake.customcake.common.CakeCategory
import javax.persistence.*

@Table(name = "cake_item")
@Entity
class CakeItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    // @ManyToOne
    @Column(name = "store_id", nullable = false)
    val storeId: Long,

    @Column(columnDefinition = "String", length = 20, nullable = false)
    val name: String,

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "String", length = 20)
    val category: CakeCategory,

    @Column(columnDefinition = "TEXT")
    val description: String? = "",

    @Column(columnDefinition = "String", length = 255, nullable = false)
    val thumbnailImageUrl: String,

    @Column(columnDefinition = "TINYINT DEFAULT 1", nullable = false)
    val isOnsale: Boolean,

    @Column(columnDefinition = "TINYINT DEFAULT 0", nullable = false)
    val isDeleted: Boolean,

    @Column(columnDefinition = "INT UNSIGNED", nullable = false)
    val price: Int,

    @Column(columnDefinition = "INT UNSIGNED DEFAULT 0", nullable = false)
    val viewCount: Int,

    @Column(columnDefinition = "INT UNSIGNED DEFAULT 0", nullable = false)
    val orderCount: Int

) : BaseEntity()
