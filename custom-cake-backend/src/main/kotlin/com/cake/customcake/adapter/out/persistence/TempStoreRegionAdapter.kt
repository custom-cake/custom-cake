package com.cake.customcake.adapter.out.persistence

import com.cake.customcake.application.port.out.LoadAllRegionsPort
import com.cake.customcake.common.TempStoreRegion
import org.springframework.stereotype.Component

@Component
class TempStoreRegionAdapter : LoadAllRegionsPort {

    override fun load(): List<String> {
        return TempStoreRegion.values().map { it.type }
    }
}