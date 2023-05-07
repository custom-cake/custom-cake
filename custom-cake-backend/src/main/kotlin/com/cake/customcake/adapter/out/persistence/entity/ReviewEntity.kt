package com.cake.customcake.adapter.out.persistence.entity

import com.cake.customcake.common.OrderOptionsInfo
import com.cake.customcake.common.OrderType
import com.cake.customcake.common.converter.JsonColumnConverter
import javax.persistence.*

@Table(name = "review")
@Entity
class ReviewEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    // @ManyToOne
    @Column(name = "user_id", nullable = false)
    val userId: Long,

    // @ManyToOne
    @Column(name = "store_id", nullable = false)
    val storeId: Long,

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "String", length = 10, nullable = false)
    val orderType: OrderType,

    @Convert(converter = JsonColumnConverter.OrderOptionsInfoConverter::class)
    @Column(name = "order_options_info", columnDefinition = "JSON", nullable = false)
    val orderOptionsInfo:  OrderOptionsInfo,

    @Column(name = "order_id", nullable = false)
    val orderId: Long,

    @Column(columnDefinition = "String", length = 255, nullable = false)
    val content: String,

    @Column(columnDefinition = "INT UNSIGNED", nullable = false)
    val score: Int,

) : BaseEntity()