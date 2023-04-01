package com.cake.customcakebackend.application.service

import com.cake.customcakebackend.application.port.out.LoadStoresByNameUserPort
import org.springframework.stereotype.Service

@Service
class StoreSearchService(
    private val loadStoresByNameUserPort: LoadStoresByNameUserPort
) {

    fun searchByName(query: String) {
        loadStoresByNameUserPort.loadByName(query)
    }
}