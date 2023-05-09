package com.cake.customcake.adapter.out.persistence.entity

import com.cake.customcake.common.MessageSenderType
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

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "String", length = 10, nullable = false)
    val senderType: MessageSenderType,

    @Column(length = 100, nullable = false)
    val sender: String,

    @Column(length = 100, nullable = false)
    val receiver: String,

    @Column(columnDefinition = "TEXT", nullable = false)
    val message: String,

    @Column(columnDefinition = "TINYINT DEFAULT 0", nullable = false)
    val isRead: Boolean

) : BaseEntity()