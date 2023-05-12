package com.cake.customcake.domain

abstract class CakeOption {
    abstract fun toResponse(): OptionResponse

    data class OptionResponse(
        val id: Long,
        val type: Int,  // 1, 2, 3
        val value: String,  // ex "원형, 1호, 1단"
        val price: Int,
    )
}
