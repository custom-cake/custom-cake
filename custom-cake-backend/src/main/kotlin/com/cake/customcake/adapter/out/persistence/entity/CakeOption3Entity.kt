package com.cake.customcake.adapter.out.persistence.entity

import javax.persistence.*

@Table(name = "cake_option3")
@Entity
class CakeOption3Entity(

    // @ManyToOne
    @Column(name = "store_id", nullable = false)
    val storeId: Long,

    @Column(columnDefinition = "String", length = 20, nullable = false)
    val name: String,

    @Column(columnDefinition = "INT UNSIGNED", nullable = false)
    val price: Int,

    @Column(columnDefinition = "TINYINT DEFAULT 1", nullable = false)
    val isUsed: Boolean,

    @Column(columnDefinition = "TINYINT DEFAULT 0", nullable = false)
    val isDeleted: Boolean

) : CakeOptionEntity() {
    override fun getType(): Int = 3
}
