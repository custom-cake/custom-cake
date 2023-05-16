package com.cake.customcake.adapter.out.persistence.entity

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
//    @Column(name = "chat_room_id", nullable = false)
//    val chatRoomId: Long,

    // @ManyToOne
    @Column(name = "user_id", nullable = false)
    val userId: Long,

    @Column(name = "store_id", nullable = false)
    val storeId: Long,

    @Column(name = "cake_custom_image_url", nullable = false)
    var cakeCustomImageUrl: String,

    @Column(name = "option1_id", nullable = false)
    val option1Id: Long,

    @Column(name = "option2_id", nullable = false)
    val option2Id: Long,

    @Convert(converter = JsonColumnConverter.ListConverter::class)
    @Column(name = "option3_id_list")
    val option3IdList: List<Long> = listOf(),

    @Column(columnDefinition = "String", length = 255)
    val userRequirements: String,

    @Column(columnDefinition = "String", length = 255)
    val operatorRequirements: String,

    @Column(columnDefinition = "INT UNSIGNED", nullable = false)
    val paymentAmount: Int,

    val pickupDatetime: LocalDateTime,

    ) : BaseEntity()