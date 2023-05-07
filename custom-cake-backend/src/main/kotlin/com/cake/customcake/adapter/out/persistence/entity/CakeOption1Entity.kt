package com.cake.customcake.adapter.out.persistence.entity

import com.cake.customcake.common.CakeOption1Type
import javax.persistence.*

@Table(name = "cake_option1")
@Entity
class CakeOption1Entity(

    // @ManyToOne
    @Column(name = "store_id", nullable = false)
    val storeId: Long,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val cakeShape: CakeOption1Type.CakeShape,  // ENUM(CIRCLE,SQUARE,HEART)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val cakeSize: CakeOption1Type.CakeSize,  // ENUM(NO_1, ... ,NO_6)

    @Column(columnDefinition = "INT UNSIGNED", nullable = false)
    val letteringLimit: Int,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val cakeLayer: CakeOption1Type.CakeLayer,  //  ENUM(LAYER_1,LAYER_2,LAYER_3)

    @Column(columnDefinition = "INT UNSIGNED", nullable = false)
    val price: Int,

    @Column(columnDefinition = "TINYINT DEFAULT 1", nullable = false)
    val isUsed: Boolean,

    @Column(columnDefinition = "TINYINT DEFAULT 0", nullable = false)
    val isDeleted: Boolean

) : CakeOptionEntity() {
    override fun getType(): Int = 1
}
