package com.cake.customcakebackend.adapter.out.persistence.entity

import com.cake.customcakebackend.domain.CakeOption1Type
import lombok.Getter
import javax.persistence.*

@Getter
@Table(name = "cake_option1")
@Entity
class CakeOption1Entity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id")
    val store: StoreEntity,

    @Column(columnDefinition = "String", length = 10, nullable = false)
    val cakeShape: CakeOption1Type.CakeSpace,  // ENUM(CIRCLE,SQUARE,HEART)

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

) : BaseEntity()
