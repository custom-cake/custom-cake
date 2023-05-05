package com.cake.customcake.adapter.out.persistence.entity

import com.cake.customcake.common.OrderStatus
import com.cake.customcake.common.converter.JsonColumnConverter
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Table(name = "cake_design_order")
@Entity
class CakeDesignOrderEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    // @ManyToOne
    @Column(name = "user_id", nullable = false)
    val userId: Long,

    @Column(name = "store_id", nullable = false)
    val storeId: Long,

    @Column(name = "cake_item_id", nullable = false)
    val cakeItemId: Long,

    @Convert(converter = JsonColumnConverter.ListConverter::class)
    @Column(name = "option_by_cake_id_list", columnDefinition = "JSON", nullable = false)
    val optionByCakeIdList: List<Long>,

    @Column(columnDefinition = "String", length = 255)
    val requirements: String,

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "String", length = 20, nullable = false)
    val orderStatus: OrderStatus,

    @Column(columnDefinition = "INT UNSIGNED", nullable = false)
    val paymentAmount: Int,

    val pickupDatetime: LocalDateTime,

    val purchaseConfirmationDate: LocalDate? = null

) : BaseEntity()