package com.cake.customcake.adapter.`in`.web

import com.cake.customcake.adapter.`in`.web.dto.request.StoreOptionSearchRequest
import com.cake.customcake.adapter.`in`.web.dto.response.StoreGetResponse
import com.cake.customcake.application.service.StoreSearchService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/stores/search")
class StoreSearchApiController(
    private val storeSearchService: StoreSearchService
) {

    @GetMapping
    fun searchByName(@RequestParam("query") query: String): List<StoreGetResponse> {
        return storeSearchService.searchByName(query)
    }

    @GetMapping("/region")
    fun getRegionList(): List<String> {
        return storeSearchService.getAllRegionsName()
    }

    @GetMapping("/option")
    fun searchByOption(@RequestBody request: StoreOptionSearchRequest): List<StoreGetResponse> {
        return storeSearchService.searchByOption(request)
    }
}