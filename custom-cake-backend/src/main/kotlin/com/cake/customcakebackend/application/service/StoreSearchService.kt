package com.cake.customcakebackend.application.service

import com.cake.customcakebackend.adapter.`in`.web.dto.request.StoreOptionSearchRequest
import com.cake.customcakebackend.adapter.`in`.web.dto.response.StoreGetResponse
import com.cake.customcakebackend.application.port.`in`.StoreSearchUseCase
import com.cake.customcakebackend.application.port.out.LoadAllRegionsUserPort
import com.cake.customcakebackend.application.port.out.LoadStoresByNameUserPort
import com.cake.customcakebackend.application.port.out.LoadStoresByOptionUserPort
import org.springframework.stereotype.Service

@Service
class StoreSearchService(
    private val loadStoresByNameUserPort: LoadStoresByNameUserPort,
    private val loadStoresByOptionUserPort: LoadStoresByOptionUserPort,
    private val loadAllRegionsUserPort: LoadAllRegionsUserPort
) : StoreSearchUseCase {

    override fun searchByName(query: String): List<StoreGetResponse> {
        return loadStoresByNameUserPort.loadByName(query)
            .map { StoreGetResponse(it.id, it.name) }
    }

    override fun getAllRegionsName(): List<String> {
        return loadAllRegionsUserPort.load()
    }

    override fun searchByOption(request: StoreOptionSearchRequest): List<StoreGetResponse> {
        return loadStoresByOptionUserPort.loadByOption(request)
            .map { StoreGetResponse(it.id, it.name) }
    }
}