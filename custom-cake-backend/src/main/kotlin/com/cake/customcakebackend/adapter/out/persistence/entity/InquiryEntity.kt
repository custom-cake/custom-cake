package com.cake.customcakebackend.adapter.out.persistence.entity

import javax.persistence.*

@Table(name = "inquiry")
@Entity
class InquiryEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    val user: UserEntity,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id")
    val store: StoreEntity,

    @Column(columnDefinition = "String", length = 50, nullable = false)
    val title: String,

    @Column(columnDefinition = "TEXT", nullable = false)
    val content: String,

    @Column(columnDefinition = "TINYINT DEFAULT 0", nullable = false)
    val isAnswered: Boolean,

    @Column(columnDefinition = "TEXT")
    val answer: String? = ""

) : BaseEntity()