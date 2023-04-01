package com.cake.customcakebackend.adapter.`in`.web

import com.cake.customcakebackend.application.service.StoreSearchService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/store/search")
class StoreSearchApiController(
    private val storeSearchService: StoreSearchService
) {

    @GetMapping("/{query}")
    fun searchByName(@PathVariable("query") query: String) {
        storeSearchService.searchByName(query)
    }
}