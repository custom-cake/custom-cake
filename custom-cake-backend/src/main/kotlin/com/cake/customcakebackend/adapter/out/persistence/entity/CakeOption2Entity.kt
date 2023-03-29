package com.cake.customcakebackend.adapter.out.persistence.entity

import com.cake.customcakebackend.domain.CakeOption2Type
import lombok.Getter
import javax.persistence.*

@Getter
@Table(name = "cake_option2")
@Entity
class CakeOption2Entity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id")
    val store: StoreEntity,

    @Column(columnDefinition = "String", length = 10, nullable = false)
    val cakeSheet: CakeOption2Type.CakeSheet,  // ENUM(CHOCO, BANILA)

    @Column(columnDefinition = "String", length = 10, nullable = false)
    val cakeInnerCream: CakeOption2Type.CakeInnerCream,  // ENUM(CREAMCHEESE, CHOCO,...)

    @Column(columnDefinition = "String", length = 10, nullable = false)
    val cakeOuterCream: CakeOption2Type.CakeOuterCream,  // ENUM(CREAMCHEESE, CHOCO,...)

    @Column(columnDefinition = "INT UNSIGNED", nullable = false)
    val price: Int,

    @Column(columnDefinition = "TINYINT DEFAULT 1", nullable = false)
    val isUsed: Boolean

) : BaseEntity()
