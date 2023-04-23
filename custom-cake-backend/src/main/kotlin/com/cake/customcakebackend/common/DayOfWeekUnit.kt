package com.cake.customcakebackend.common

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import java.time.LocalDate

enum class DayOfWeekUnit(@get:JsonValue val type: String) {
    SUN("SUN"),
    MON("MON"),
    TUE("TUE"),
    WED("WED"),
    THR("THR"),
    FRI("FRI"),
    SAT("SAT");

    companion object {
        @JsonCreator
        fun set(type: String): DayOfWeekUnit? = DayOfWeekUnit.values().find { it.type == type }

        private val shareTypeMap = DayOfWeekUnit.values().associateBy(DayOfWeekUnit::type)
        fun find(type: String): DayOfWeekUnit? = shareTypeMap[type]
        fun toList(): List<String> = DayOfWeekUnit.values().map { it.type }

        fun of(localDate: LocalDate): DayOfWeekUnit {
            return when (localDate.dayOfWeek) {
                java.time.DayOfWeek.SUNDAY -> SUN
                java.time.DayOfWeek.MONDAY -> MON
                java.time.DayOfWeek.TUESDAY -> TUE
                java.time.DayOfWeek.WEDNESDAY -> WED
                java.time.DayOfWeek.THURSDAY -> THR
                java.time.DayOfWeek.FRIDAY -> FRI
                java.time.DayOfWeek.SATURDAY -> SAT
            }
        }
    }
}