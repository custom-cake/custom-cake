package com.cake.customcakebackend.application.port.out

import com.cake.customcakebackend.domain.CakeDesignOrder

interface CakeDesignOrderPort {
    fun save(cakeDesignOrder: CakeDesignOrder)
    fun loadList(userId: Long): List<CakeDesignOrder>
}