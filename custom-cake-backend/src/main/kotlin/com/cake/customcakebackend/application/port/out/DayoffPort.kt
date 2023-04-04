package com.cake.customcakebackend.application.port.out

import com.cake.customcakebackend.domain.Dayoff
import java.util.Date

interface DayoffPort {

    fun saveFixedDayoff(fixedDayoffList: List<Dayoff>)
    fun saveDesignedDayoff(designedDayoffList: List<Date>)
}