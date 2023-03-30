package com.cake.customcakebackend.common

class CakeOption1Type {
    enum class CakeSpace(val type: String) {
        CIRCLE("원형"),
        SQUARE("사각형"),
        HEART("하트")
    }

    enum class CakeSize(val type: Int) {
        NO_1(1),
        NO_2(2),
        NO_3(3),
        NO_4(4),
        NO_5(5),
        NO_6(6)
    }

    enum class CakeLayer(val type: Int) {
        LAYER_1(1),
        LAYER_2(2),
        LAYER_3(3)
    }
}