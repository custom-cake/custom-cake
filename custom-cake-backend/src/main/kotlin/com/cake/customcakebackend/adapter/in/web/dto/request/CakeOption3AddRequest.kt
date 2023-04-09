package com.cake.customcakebackend.adapter.`in`.web.dto.request

import org.jetbrains.annotations.NotNull


data class CakeOption3AddRequest(
    @NotNull
    val name: String? = null,
    val price: Int = 0
)
