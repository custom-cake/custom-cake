package com.cake.customcake.application.port.out

import com.cake.customcake.domain.CakeCustomOrderSheet

interface CakeCustomOrderSheetPort {
    fun load(sheetId: Long) : CakeCustomOrderSheet
    fun save(cakeCustomOrderSheet: CakeCustomOrderSheet)
    fun hasSheet(storeId: Long, userId: Long): Pair<Boolean, Long?>
    fun updateImage(sheet: CakeCustomOrderSheet, url: String): Long
}