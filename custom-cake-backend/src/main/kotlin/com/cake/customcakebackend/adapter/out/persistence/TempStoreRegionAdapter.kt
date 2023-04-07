package com.cake.customcakebackend.adapter.out.persistence

import com.cake.customcakebackend.application.port.out.LoadAllRegionsPort
import com.cake.customcakebackend.common.TempStoreRegion
import org.springframework.stereotype.Component

@Component
class TempStoreRegionAdapter : LoadAllRegionsPort {

    override fun load(): List<String> {
        return TempStoreRegion.values().map { it.type }
    }
}