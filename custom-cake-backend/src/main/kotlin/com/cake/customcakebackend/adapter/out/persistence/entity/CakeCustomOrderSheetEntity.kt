package com.cake.customcakebackend.adapter.out.persistence.entity

import com.cake.customcakebackend.common.JsonColumnConverter
import com.cake.customcakebackend.common.CakeCustomSketch
import java.time.LocalDateTime
import javax.persistence.*

@Table(name = "cake_custom_order_sheet")
@Entity
class CakeCustomOrderSheetEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    // 하나의 채팅에서 커스텀 주문이 여러 번 있을 수 있음 -> ManyToOne
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "chat_room_id")
    val chatRoom: ChatRoomEntity,

    val pickupDatetime: LocalDateTime,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cake_option1_id")
    val cakeOption1: CakeOption1Entity,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cake_option2_id")
    val cakeOption2: CakeOption2Entity,

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "cake_option3_id")
    val cakeOption3: CakeOption3Entity? = null,

    @Column(columnDefinition = "String", length = 255, nullable = false)
    val cakeCustomImage: String,

    @Convert(converter = JsonColumnConverter.CakeCustomSketchConverter::class)
    @Column(columnDefinition = "JSON", nullable = false)
    val cakeCustomSketch: CakeCustomSketch

) : BaseEntity()