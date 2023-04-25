package com.cake.customcakebackend.application.port.out

import com.cake.customcakebackend.domain.CakeDesignOrder

interface SaveCakeDesignOrderPort {
    fun save(cakeDesignOrder: CakeDesignOrder)
}