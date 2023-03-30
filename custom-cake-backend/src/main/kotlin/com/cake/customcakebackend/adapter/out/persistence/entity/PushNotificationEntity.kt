package com.cake.customcakebackend.adapter.out.persistence.entity

import com.cake.customcakebackend.common.OrderType
import javax.persistence.*

@Table(name = "push_notification")
@Entity
class PushNotificationEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    val user: UserEntity,

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "String", length = 10, nullable = false)
    val orderType: OrderType,

    @Column(columnDefinition = "BIGINT UNSIGNED")
    val orderId: Long,

    @Column(columnDefinition = "String", length = 255, nullable = false)
    val message: String,

) : BaseEntity()