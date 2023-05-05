package com.cake.customcake.application.service

import com.cake.customcake.adapter.`in`.web.dto.request.StoreOptionSearchRequest
import com.cake.customcake.adapter.`in`.web.dto.response.StoreGetResponse
import com.cake.customcake.application.port.`in`.StoreSearchUseCase
import com.cake.customcake.application.port.out.LoadAllRegionsPort
import com.cake.customcake.application.port.out.LoadStoresByNamePort
import com.cake.customcake.application.port.out.LoadStoresByOptionPort
import org.springframework.stereotype.Service

@Service
class StoreSearchService(
    private val loadStoresByNamePort: LoadStoresByNamePort,
    private val loadStoresByOptionPort: LoadStoresByOptionPort,
    private val loadAllRegionsPort: LoadAllRegionsPort
) : StoreSearchUseCase {

    override fun searchByName(query: String): List<StoreGetResponse> {
        return loadStoresByNamePort.loadByName(query)
            .map { StoreGetResponse(it.id, it.name) }
    }

    override fun getAllRegionsName(): List<String> {
        return loadAllRegionsPort.load()
    }

    override fun searchByOption(request: StoreOptionSearchRequest): List<StoreGetResponse> {
        return loadStoresByOptionPort.loadByOption(request)
            .map { StoreGetResponse(it.id, it.name) }
    }
}