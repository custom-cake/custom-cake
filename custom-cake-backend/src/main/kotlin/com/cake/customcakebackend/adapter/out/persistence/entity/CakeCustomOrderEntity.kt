package com.cake.customcakebackend.adapter.out.persistence.entity

import com.cake.customcakebackend.common.OrderStatus
import java.time.LocalDateTime
import javax.persistence.*

@Table(name = "cake_custom_order")
@Entity
class CakeCustomOrderEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    // @ManyToOne
    @Column(name = "user_id", nullable = false)
    val userId: Long,

    @Column(name = "store_id", nullable = false)
    val storeId: Long,

    // @OneToOne
    @Column(name = "cake_custom_order_sheet_id", nullable = false)
    val cakeCustomOrderSheetId: Long,

    @Column(columnDefinition = "INT UNSIGNED", nullable = false)
    val paymentAmount: Int,

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "String", length = 20, nullable = false)
    val orderStatus: OrderStatus,

    val pickupDatetime: LocalDateTime,

) : BaseEntity()