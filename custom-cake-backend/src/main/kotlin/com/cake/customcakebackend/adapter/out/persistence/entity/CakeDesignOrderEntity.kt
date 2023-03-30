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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    val user: UserEntity,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cake_option1_id")
    val cakeOption1: CakeOption1Entity,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cake_option2_id")
    val cakeOption2: CakeOption2Entity,

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "cake_option3_id")
    val cakeOption3: CakeOption3Entity? = null,

    @Column(columnDefinition = "String", length = 255)
    val requirements: String,

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "String", length = 20, nullable = false)
    val orderStatus: OrderStatus,

    @Column(columnDefinition = "INT UNSIGNED", nullable = false)
    val paymentAmount: Int,

    val purchaseConfirmationDate: LocalDate

) : BaseEntity()