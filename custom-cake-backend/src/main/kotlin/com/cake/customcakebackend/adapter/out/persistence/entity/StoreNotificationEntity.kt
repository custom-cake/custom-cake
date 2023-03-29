package com.cake.customcakebackend.adapter.out.persistence.entity

import lombok.Getter
import java.time.LocalDate
import javax.persistence.*

@Getter
@Table(name = "store_notification")
@Entity
class StoreNotificationEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id")
    val store: StoreEntity,

    @Column(columnDefinition = "String", length = 255, nullable = false)
    val title: String,

    @Column(columnDefinition = "TEXT", nullable = false)
    val content: String,

): BaseEntity()