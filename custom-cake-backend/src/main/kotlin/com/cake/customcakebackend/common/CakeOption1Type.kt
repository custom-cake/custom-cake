package com.cake.customcakebackend.common

class CakeOption1Type {
    enum class CakeShape(val type: String) {
        CIRCLE("원형"),
        SQUARE("사각형"),
        HEART("하트")
    }

    enum class CakeSize(val type: String) {
        NO_0("도시락"),
        NO_1("1호"),
        NO_2("2호"),
        NO_3("3호"),
        NO_4("4호"),
        NO_5("5호"),
        NO_6("6호")
    }

    enum class CakeLayer(val type: String) {
        LAYER_1("1단"),
        LAYER_2("2단"),
        LAYER_3("3단")
    }
}