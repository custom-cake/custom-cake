package com.cake.customcakebackend.adapter.out.persistence.entity

import com.cake.customcakebackend.common.CakeOption1Type
import javax.persistence.*

@Table(name = "cake_option1")
@Entity
class CakeOption1Entity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    // @ManyToOne
    @Column(name = "store_id", nullable = false)
    val storeId: Long,

    @Column(columnDefinition = "String", length = 10, nullable = false)
    val cakeShape: CakeOption1Type.CakeShape,  // ENUM(CIRCLE,SQUARE,HEART)

    @Column(columnDefinition = "String", length = 10, nullable = false)
    val cakeSize: CakeOption1Type.CakeSize,  // ENUM(NO_1, ... ,NO_6)

    @Column(columnDefinition = "INT UNSIGNED", nullable = false)
    val letteringLimit: Int,

    @Column(columnDefinition = "String", length = 10, nullable = false)
    val cakeLayer: CakeOption1Type.CakeLayer,  //  ENUM(LAYER_1,LAYER_2,LAYER_3)

    @Column(columnDefinition = "INT UNSIGNED", nullable = false)
    val price: Int,

    @Column(columnDefinition = "TINYINT DEFAULT 1", nullable = false)
    val isUsed: Boolean,

    @Column(columnDefinition = "TINYINT DEFAULT 0", nullable = false)
    val isDeleted: Boolean

) : BaseEntity()
