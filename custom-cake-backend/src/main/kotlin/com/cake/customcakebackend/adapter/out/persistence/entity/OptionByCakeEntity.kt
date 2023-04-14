package com.cake.customcakebackend.adapter.out.persistence.entity

import javax.persistence.*

@Table(name = "option_by_cake")
@Entity
class OptionByCakeEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    // @ManyToOne
    @Column(name = "cake_item_id", nullable = false)
    val cakeItemId: Long,

    @Column(columnDefinition = "INT UNSIGNED", nullable = false)
    val cakeOptionType: Int,  // 1, 2, 3

    @Column(columnDefinition = "BIGINT UNSIGNED", nullable = false)
    val cakeOptionId: Long,  // CakeOption's id

    @Column(nullable = false)
    val cakeOptionValue: String,

    @Column(columnDefinition = "INT UNSIGNED", nullable = false)
    val price: Int,

    @Column(columnDefinition = "TINYINT DEFAULT 1", nullable = false)
    val isUsed: Boolean

) : BaseEntity()