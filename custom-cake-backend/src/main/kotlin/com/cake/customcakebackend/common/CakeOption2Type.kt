package com.cake.customcakebackend.common

class CakeOption2Type {
    enum class CakeSheet(val type: String) {
        BANILLA_SHEET("바닐라시트"),
        CHOCO_SHEET("초코시트"),
        EARL_GREY_SHEET("얼그레이시트"),
        MOCHA_SHEET("모카시트"),
        MATCHA_SHEET("말차시트")
    }

    enum class CakeInnerCream(val type: String) {
        CREAMCHEESE("크림치즈"),
        CHOCO("초코크림"),
        COOKIE("쿠키크림"),
        BUTTER("버터크림"),
        MILK("우유생크림"),
        STRAWBERRY("딸기생크림"),
        RASPBERRY("라즈베리크림"),
        BLUEBERRY("블루베리크림")
    }

    enum class CakeOuterCream(val type: String) {
        CREAMCHEESE("크림치즈"),
        CHOCO("초코크림"),
        COOKIE("쿠키크림"),
        BUTTER("버터크림"),
        MILK("우유생크림"),
        STRAWBERRY("딸기생크림"),
        RASPBERRY("라즈베리크림"),
        BLUEBERRY("블루베리크림")
    }
}