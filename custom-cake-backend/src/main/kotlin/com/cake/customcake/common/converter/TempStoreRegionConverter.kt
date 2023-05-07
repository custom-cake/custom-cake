package com.cake.customcake.common.converter

import com.cake.customcake.common.TempStoreRegion
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = true)
class TempStoreRegionConverter :AttributeConverter<TempStoreRegion, String> {
    override fun convertToDatabaseColumn(attribute: TempStoreRegion): String {
        return attribute.type
    }

    override fun convertToEntityAttribute(type: String): TempStoreRegion {
        return TempStoreRegion.find(type) ?: throw IllegalArgumentException()
    }
}