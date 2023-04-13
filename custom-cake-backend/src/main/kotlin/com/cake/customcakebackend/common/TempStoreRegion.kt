package com.cake.customcakebackend.common

import com.fasterxml.jackson.annotation.JsonCreator

enum class TempStoreRegion(val type: String) {
    SEOUNGBUK("성북구"),
    SEOUNGDONG("성동구");


    companion object {
        @JsonCreator
        fun set(type: String): TempStoreRegion? = TempStoreRegion.values().find { it.type == type }

        private val shareTypeMap = TempStoreRegion.values().associateBy(TempStoreRegion::type)
        fun find(type: String): TempStoreRegion? = shareTypeMap[type]
        fun toList(): List<String> = TempStoreRegion.values().map { it.type }
    }
}