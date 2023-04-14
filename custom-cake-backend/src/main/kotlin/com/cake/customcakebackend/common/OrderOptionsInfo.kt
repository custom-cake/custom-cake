package com.cake.customcakebackend.common

import com.fasterxml.jackson.annotation.JsonProperty

data class OrderOptionsInfo(
    @JsonProperty("option1")
    val option1: String,
    @JsonProperty("option2")
    val option2: String,
    @JsonProperty("option3")
    val option3: String = "",
)