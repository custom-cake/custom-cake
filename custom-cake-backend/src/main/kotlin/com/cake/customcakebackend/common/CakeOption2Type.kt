package com.cake.customcakebackend.common

class CakeOption2Type {
    enum class CakeSheet(val type: String) {
        CHOCO("초코"),
        BANILLA("바닐라")
    }

    enum class CakeInnerCream(val type: String) {
        CHOCO("초코"),
        CREAMCHEESE("크림치즈")
    }

    enum class CakeOuterCream(val type: String) {
        CHOCO("초코"),
        CREAMCHEESE("크림치즈")
    }
}