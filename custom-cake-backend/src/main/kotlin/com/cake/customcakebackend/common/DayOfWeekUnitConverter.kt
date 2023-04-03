package com.cake.customcakebackend.common

import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = true)
class DayOfWeekUnitConverter : AttributeConverter<DayOfWeekUnit, String> {

    override fun convertToDatabaseColumn(attribute: DayOfWeekUnit): String {
        return attribute.type
    }

    override fun convertToEntityAttribute(type: String): DayOfWeekUnit {
        return DayOfWeekUnit.find(type) ?: throw IllegalArgumentException()
    }
}