package com.cake.customcakebackend.application.service

import com.cake.customcakebackend.adapter.`in`.web.dto.response.StoreGetResponse
import com.cake.customcakebackend.application.port.out.LoadStoresByNameUserPort
import org.springframework.stereotype.Service

@Service
class StoreSearchService(
    private val loadStoresByNameUserPort: LoadStoresByNameUserPort
) {

    fun searchByName(query: String): List<StoreGetResponse> {
        return loadStoresByNameUserPort.loadByName(query)
            .map { StoreGetResponse(it.id, it.name) }
    }
}