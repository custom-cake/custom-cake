package com.cake.customcake.application.port.out

import com.cake.customcake.domain.Dayoff
import java.util.Date

interface DayoffPort {

    fun loadDayOff(storeId: Long): List<Dayoff>
    fun saveFixedDayoff(fixedDayoffList: List<Dayoff>)
    fun saveDesignedDayoff(designedDayoffList: List<Date>)
}