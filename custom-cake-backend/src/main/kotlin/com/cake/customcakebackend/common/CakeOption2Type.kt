package com.cake.customcakebackend.common

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

class CakeOption2Type {
    enum class CakeSheet(@JsonValue val type: String) {
        BANILLA_SHEET("바닐라시트"),
        CHOCO_SHEET("초코시트"),
        EARL_GREY_SHEET("얼그레이시트"),
        MOCHA_SHEET("모카시트"),
        MATCHA_SHEET("말차시트");
        companion object {
            @JsonCreator
            fun set(type: String): CakeSheet? = CakeSheet.values().find { it.type == type }
            private val shareTypeMap = CakeSheet.values().associateBy(CakeSheet::type)
            fun find(type: String): CakeSheet? = shareTypeMap[type]
            fun toList(): List<String> = CakeSheet.values().map { it.type }
            fun toMap(): Map<CakeSheet, String> = CakeSheet.values().associateWith { it.type }
        }
    }

    enum class CakeInnerCream(@JsonValue val type: String) {
        CREAMCHEESE("크림치즈"),
        CHOCO("초코크림"),
        COOKIE("쿠키크림"),
        BUTTER("버터크림"),
        MILK("우유생크림"),
        STRAWBERRY("딸기생크림"),
        RASPBERRY("라즈베리크림"),
        BLUEBERRY("블루베리크림"),
        OREO("오레오생크림");
        companion object {
            @JsonCreator
            fun set(type: String): CakeInnerCream? = CakeInnerCream.values().find { it.type == type }
            private val shareTypeMap = CakeInnerCream.values().associateBy(CakeInnerCream::type)
            fun find(type: String): CakeInnerCream? = shareTypeMap[type]
            fun toList(): List<String> = CakeInnerCream.values().map { it.type }
            fun toMap(): Map<CakeInnerCream, String> = values().associateWith { it.type }
        }
    }

    enum class CakeOuterCream(@JsonValue val type: String) {
        CREAMCHEESE("크림치즈"),
        CHOCO("초코크림"),
        COOKIE("쿠키크림"),
        BUTTER("버터크림"),
        MILK("우유생크림"),
        STRAWBERRY("딸기생크림"),
        RASPBERRY("라즈베리크림"),
        BLUEBERRY("블루베리크림"),
        OREO("오레오생크림");
        companion object {
            @JsonCreator
            fun set(type: String): CakeOuterCream? = CakeOuterCream.values().find { it.type == type }
            private val shareTypeMap = CakeOuterCream.values().associateBy(CakeOuterCream::type)
            fun find(type: String): CakeOuterCream? = shareTypeMap[type]
            fun toList(): List<String> = CakeOuterCream.values().map { it.type }
            fun toMap(): Map<CakeOuterCream, String> = CakeOuterCream.values().associateWith { it.type }
        }
    }
}