package com.cake.customcake.common

import com.fasterxml.jackson.annotation.JsonProperty

data class CakeCustomSketch(
    // TODO("DTO 정의")
    @JsonProperty("sketch")
    val sketch: Map<String, Any>
)