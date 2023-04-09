package com.cake.customcakebackend.common

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

class CakeOption1Type {
    enum class CakeShape(@get:JsonValue val type: String) {
        CIRCLE("원형"),
        SQUARE("사각형"),
        HEART("하트");

        companion object {
            @JsonCreator
            fun set(type: String): CakeShape? = CakeShape.values().find { it.type == type }
            private val shareTypeMap = CakeShape.values().associateBy(CakeShape::type)
            fun find(type: String): CakeShape? = shareTypeMap[type]
            fun toList(): List<String> = CakeShape.values().map { it.type }
        }
    }

    enum class CakeSize(@get:JsonValue val type: String) {
        NO_0("도시락"),
        NO_1("1호"),
        NO_2("2호"),
        NO_3("3호"),
        NO_4("4호"),
        NO_5("5호"),
        NO_6("6호");
        companion object {
            @JsonCreator
            fun set(type: String): CakeSize? = CakeSize.values().find { it.type == type }
            private val shareTypeMap = CakeSize.values().associateBy(CakeSize::type)
            fun find(type: String): CakeSize? = shareTypeMap[type]
            fun toList(): List<String> = CakeSize.values().map { it.type }
        }
    }

    enum class CakeLayer(val type: String) {
        LAYER_1("1단"),
        LAYER_2("2단"),
        LAYER_3("3단");
        companion object {
            @JsonCreator
            fun set(type: String): CakeLayer? = CakeLayer.values().find { it.type == type }
            private val shareTypeMap = CakeLayer.values().associateBy(CakeLayer::type)
            fun find(type: String): CakeLayer? = shareTypeMap[type]
            fun toList(): List<String> = CakeLayer.values().map { it.type }
        }
    }
}
