package com.cake.customcake.adapter.out.persistence.entity

import com.cake.customcake.common.ChatStatus
import javax.persistence.*

@Table(name = "chat_room")
@Entity
class ChatRoomEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    // user가 동일한 매장(운영자)에 여러 번 주문하는 경우는 이전 주문 채팅 history 를 보여줄 것인가?
    // @ManyToOne
    @Column(name = "user_id", nullable = false)
    val userId: Long,

    // @ManyToOne
    @Column(name = "operator_id", nullable = false)
    val operatorId: Long,

    // 하나의 채팅에서 커스텀 주문이 여러 번 있을 수 있음 -> OneToMany
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "")
//    val customCakeOrderSheetList: List<CakeCustomOrderSheetEntity>,

    @Column(columnDefinition = "BIGINT UNSIGNED", nullable = false)
    val recentCakeCustomOrderSheetId: Long,

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "String", length = 20, nullable = false)
    val chatStatus: ChatStatus

) : BaseEntity()