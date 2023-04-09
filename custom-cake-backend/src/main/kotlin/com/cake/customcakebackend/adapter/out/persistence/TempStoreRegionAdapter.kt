package com.cake.customcakebackend.adapter.out.persistence

import com.cake.customcakebackend.application.port.out.LoadAllRegionsUserPort
import com.cake.customcakebackend.common.TempStoreRegion
import org.springframework.stereotype.Component

@Component
class TempStoreRegionAdapter : LoadAllRegionsUserPort {

    override fun load(): List<String> {
        return TempStoreRegion.values().map { it.type }
    }
}