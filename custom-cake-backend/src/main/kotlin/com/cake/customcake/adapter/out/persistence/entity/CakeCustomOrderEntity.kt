package com.cake.customcake.adapter.out.persistence.entity

import com.cake.customcake.common.OrderStatus
import com.cake.customcake.common.converter.JsonColumnConverter
import java.time.LocalDate
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

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cake_custom_order_sheet_id")
    val cakeCustomOrderSheet: CakeCustomOrderSheetEntity,

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "String", length = 20, nullable = false)
    val orderStatus: OrderStatus,

    @Column(columnDefinition = "INT UNSIGNED", nullable = false)
    val paymentAmount: Int,

    val pickupDatetime: LocalDateTime,

    val purchaseConfirmationDate: LocalDate? = null

) : BaseEntity()