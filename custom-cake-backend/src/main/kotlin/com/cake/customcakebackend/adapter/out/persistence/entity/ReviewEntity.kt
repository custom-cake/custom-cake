package com.cake.customcakebackend.adapter.out.persistence.entity

import com.cake.customcakebackend.common.OrderType
import javax.persistence.*

@Table(name = "review")
@Entity
class ReviewEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    val user: UserEntity,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id")
    val store: StoreEntity,

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "String", length = 10, nullable = false)
    val orderType: OrderType,

    @Column(columnDefinition = "BIGINT UNSIGNED")
    val orderId: Long,

    @Column(columnDefinition = "String", length = 255, nullable = false)
    val content: String,

    @Column(columnDefinition = "INT UNSIGNED", nullable = false)
    val score: Int,

) : BaseEntity()