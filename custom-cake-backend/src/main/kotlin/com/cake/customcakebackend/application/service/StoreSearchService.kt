package com.cake.customcakebackend.application.service

import com.cake.customcakebackend.adapter.`in`.web.dto.response.StoreGetResponse
import com.cake.customcakebackend.application.port.`in`.StoreSearchUseCase
import com.cake.customcakebackend.application.port.out.LoadAllRegionsPort
import com.cake.customcakebackend.application.port.out.LoadStoresByNameUserPort
import org.springframework.stereotype.Service

@Service
class StoreSearchService(
    private val loadStoresByNameUserPort: LoadStoresByNameUserPort,
    private val loadAllRegionsPort: LoadAllRegionsPort
) : StoreSearchUseCase {

    override fun searchByName(query: String): List<StoreGetResponse> {
        return loadStoresByNameUserPort.loadByName(query)
            .map { StoreGetResponse(it.id, it.name) }
    }

    override fun getAllRegionsName(): List<String> {
        return loadAllRegionsPort.load()
    }
}