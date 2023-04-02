package com.cake.customcakebackend.adapter.out.persistence.entity

import com.cake.customcakebackend.common.OrderType
import javax.persistence.*

@Table(name = "push_notification")
@Entity
class PushNotificationEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    // @ManyToOne
    @Column(name = "user_id", nullable = false)
    val userId: Long,

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "String", length = 10, nullable = false)
    val orderType: OrderType,

    @Column(name = "order_id", nullable = false)
    val orderId: Long,

    @Column(columnDefinition = "String", length = 255, nullable = false)
    val message: String,

) : BaseEntity()