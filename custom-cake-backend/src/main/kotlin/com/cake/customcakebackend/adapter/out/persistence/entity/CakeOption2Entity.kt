package com.cake.customcakebackend.adapter.out.persistence.entity

import com.cake.customcakebackend.common.CakeOption2Type
import javax.persistence.*

@Table(name = "cake_option2")
@Entity
class CakeOption2Entity(

    // @ManyToOne
    @Column(name = "store_id", nullable = false)
    val storeId: Long,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val cakeSheet: CakeOption2Type.CakeSheet,  // ENUM(CHOCO, BANILA)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val cakeInnerCream: CakeOption2Type.CakeInnerCream,  // ENUM(CREAMCHEESE, CHOCO,...)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val cakeOuterCream: CakeOption2Type.CakeOuterCream,  // ENUM(CREAMCHEESE, CHOCO,...)

    @Column(columnDefinition = "INT UNSIGNED", nullable = false)
    val price: Int,

    @Column(columnDefinition = "TINYINT DEFAULT 1", nullable = false)
    val isUsed: Boolean,

    @Column(columnDefinition = "TINYINT DEFAULT 0", nullable = false)
    val isDeleted: Boolean

) : CakeOptionEntity() {
    override fun getType(): Int = 2
}
