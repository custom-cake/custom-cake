package com.cake.customcake.domain

import com.cake.customcake.common.CakeCustomSketch
import java.time.LocalDateTime

data class CakeCustomOrderSheet (
    val id: Long,
    val chatRoomId: Long,
    val pickupDatetime: LocalDateTime,
    val cakeOption1: CakeOption1,
    val cakeOption2: CakeOption2,
    val cakeOption3: CakeOption3? = null,
    val cakeCustomImage: String,
    val cakeCustomSketch: CakeCustomSketch,
    val createdAt: LocalDateTime,
    var modifiedAt: LocalDateTime
)