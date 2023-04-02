package com.cake.customcakebackend.adapter.out.persistence.entity

import javax.persistence.*

@Table(name = "store_notification")
@Entity
class StoreNotificationEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    // @ManyToOne
    @Column(name = "store_id", nullable = false)
    val storeId: Long,

    @Column(columnDefinition = "String", length = 255, nullable = false)
    val title: String,

    @Column(columnDefinition = "TEXT", nullable = false)
    val content: String,

) : BaseEntity()