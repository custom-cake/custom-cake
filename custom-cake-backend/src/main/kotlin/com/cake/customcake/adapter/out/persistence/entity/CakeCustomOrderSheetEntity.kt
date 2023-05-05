package com.cake.customcake.adapter.out.persistence.entity

import com.cake.customcake.common.CakeCustomSketch
import com.cake.customcake.common.converter.JsonColumnConverter
import java.time.LocalDateTime
import javax.persistence.*

@Table(name = "cake_custom_order_sheet")
@Entity
class CakeCustomOrderSheetEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    // 하나의 채팅에서 커스텀 주문이 여러 번 있을 수 있음
    // @ManyToOne
    @Column(name = "chat_room_id", nullable = false)
    val chatRoomId: Long,

    val pickupDatetime: LocalDateTime,

    // @ManyToOne
    @Column(name = "cake_option1_id", nullable = false)
    val cakeOption1Id: Long,

    // @ManyToOne
    @Column(name = "cake_option2_id", nullable = false)
    val cakeOption2Id: Long,

    // @ManyToOne
    @Column(name = "cake_option3_id")
    val cakeOption3Id: Long? = null,

    @Column(columnDefinition = "String", length = 255, nullable = false)
    val cakeCustomImage: String,

    @Convert(converter = JsonColumnConverter.CakeCustomSketchConverter::class)
    @Column(columnDefinition = "JSON", nullable = false)
    val cakeCustomSketch: CakeCustomSketch

) : BaseEntity()