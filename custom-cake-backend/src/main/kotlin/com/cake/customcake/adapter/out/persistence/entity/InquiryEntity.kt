package com.cake.customcake.adapter.out.persistence.entity

import javax.persistence.*

@Table(name = "inquiry")
@Entity
class InquiryEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    // @ManyToOne
    @Column(name = "user_id", nullable = false)
    val userId: Long,

    // @ManyToOne
    @Column(name = "store_id", nullable = false)
    val storeId: Long,

    @Column(columnDefinition = "String", length = 50, nullable = false)
    val title: String,

    @Column(columnDefinition = "TEXT", nullable = false)
    val content: String,

    @Column(columnDefinition = "TINYINT DEFAULT 0", nullable = false)
    val isAnswered: Boolean,

    @Column(columnDefinition = "TEXT")
    val answer: String? = ""

) : BaseEntity()