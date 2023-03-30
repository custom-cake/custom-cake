package com.cake.customcakebackend.adapter.out.persistence.entity

import javax.persistence.*

@Table(name = "chat_message")
@Entity
class ChatMessageEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "chat_room_id")
    val chatRoom: ChatRoomEntity,

    @Column(columnDefinition = "TEXT", nullable = false)
    val message: String,

    @Column(columnDefinition = "TINYINT DEFAULT 0", nullable = false)
    val isRead: Boolean

) : BaseEntity()