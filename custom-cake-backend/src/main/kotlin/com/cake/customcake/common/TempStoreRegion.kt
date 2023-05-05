package com.cake.customcake.common

import com.fasterxml.jackson.annotation.JsonCreator

enum class TempStoreRegion(val type: String) {
    JONGNO("종로구"),
    JUNG("중구"),
    YONGSAN("용산구"),
    SEOUNGDONG("성동구"),
    GWANGJIN("광진구"),
    DONGDAEMUN("동대문구"),
    JUNGNANG("중랑구"),
    SEOUNGBUK("성북구"),
    GANGBUK("강북구"),
    DOBONG("도봉구"),
    NOWON("노원구"),
    EUNPYEONG("은평구"),
    SEODAEMUN("서대문구"),
    MAPO("마포구"),
    YANGCHEON("양천구"),
    GANGSEO("강서구"),
    GURO("구로구"),
    GEUMCHEON("금천구"),
    YEONGDEUNGPO("영등포구"),
    DONGJAK("동작구"),
    GWANAK("관악구"),
    SEOCHO("서초구"),
    GANGNAM("강남구"),
    SONGPA("송파구"),
    GANGDONG("강동구");


    companion object {
        @JsonCreator
        fun set(type: String): TempStoreRegion? = TempStoreRegion.values().find { it.type == type }

        private val shareTypeMap = TempStoreRegion.values().associateBy(TempStoreRegion::type)
        fun find(type: String): TempStoreRegion? = shareTypeMap[type]
        fun toList(): List<String> = TempStoreRegion.values().map { it.type }
    }
}