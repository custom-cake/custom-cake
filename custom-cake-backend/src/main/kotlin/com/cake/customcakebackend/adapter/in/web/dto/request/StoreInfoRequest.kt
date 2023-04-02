package com.cake.customcakebackend.adapter.`in`.web.dto.request

import javax.validation.constraints.NotNull

data class StoreInfoRequest(
    @NotNull
    val operatorId: Long
)