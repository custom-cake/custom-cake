package com.cake.customcakebackend.adapter.out.persistence.entity

import com.cake.customcakebackend.common.OrderStatus
import java.time.LocalDate
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

    // @ManyToOne
    @Column(name = "cake_option1_id", nullable = false)
    val cakeOption1Id: Long,

    // @ManyToOne
    @Column(name = "cake_option2_id", nullable = false)
    val cakeOption2Id: Long,

    // @ManyToOne
    @Column(name = "cake_option3_id")
    val cakeOption3Id: Long? = null,

    @Column(columnDefinition = "String", length = 255)
    val requirements: String,

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "String", length = 20, nullable = false)
    val orderStatus: OrderStatus,

    @Column(columnDefinition = "INT UNSIGNED", nullable = false)
    val paymentAmount: Int,

    val purchaseConfirmationDate: LocalDate

) : BaseEntity()