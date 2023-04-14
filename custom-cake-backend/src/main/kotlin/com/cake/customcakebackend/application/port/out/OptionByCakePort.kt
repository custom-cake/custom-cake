package com.cake.customcakebackend.application.port.out

import com.cake.customcakebackend.domain.OptionByCake

interface OptionByCakePort {
    fun loadList(cakeItemId: Long): List<OptionByCake>
}