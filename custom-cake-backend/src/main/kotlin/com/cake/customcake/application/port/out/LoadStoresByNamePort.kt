package com.cake.customcake.application.port.out

import com.cake.customcake.domain.Store

interface LoadStoresByNamePort {
    fun loadByName(query: String): List<Store>
}