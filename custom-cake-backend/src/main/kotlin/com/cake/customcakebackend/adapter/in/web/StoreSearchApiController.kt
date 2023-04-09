package com.cake.customcakebackend.adapter.`in`.web

import com.cake.customcakebackend.adapter.`in`.web.dto.response.StoreGetResponse
import com.cake.customcakebackend.application.service.StoreSearchService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/store/search")
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
}