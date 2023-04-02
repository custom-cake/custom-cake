package com.cake.customcakebackend.adapter.out.persistence.entity

import javax.persistence.*

@Table(name = "chat_message")
@Entity
class ChatMessageEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    // @ManyToOne
    @Column(name = "chat_room_id", nullable = false)
    val chatRoomId: Long,

    @Column(columnDefinition = "TEXT", nullable = false)
    val message: String,

    @Column(columnDefinition = "TINYINT DEFAULT 0", nullable = false)
    val isRead: Boolean

) : BaseEntity()