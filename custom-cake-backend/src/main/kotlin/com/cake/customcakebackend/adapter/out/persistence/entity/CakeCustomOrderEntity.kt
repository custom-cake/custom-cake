package com.cake.customcakebackend.adapter.out.persistence.entity

import com.cake.customcakebackend.common.OrderStatus
import javax.persistence.*

@Table(name = "cake_custom_order")
@Entity
class CakeCustomOrderEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    val user: UserEntity,

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cake_custom_order_sheet_id")
    val customCakeOrderSheet: CakeCustomOrderSheetEntity,

    @Column(columnDefinition = "INT UNSIGNED", nullable = false)
    val paymentAmount: Int,

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "String", length = 20, nullable = false)
    val orderStatus: OrderStatus,

) : BaseEntity()