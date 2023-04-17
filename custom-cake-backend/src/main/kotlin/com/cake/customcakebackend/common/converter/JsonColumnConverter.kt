package com.cake.customcakebackend.common.converter

import com.cake.customcakebackend.common.CakeCustomSketch
import com.cake.customcakebackend.common.DayOfWeekUnit
import com.cake.customcakebackend.common.OrderOptionsInfo
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.IOException
import javax.persistence.AttributeConverter
import javax.persistence.Converter

private val mapper: ObjectMapper = ObjectMapper()
class JsonColumnConverter {

    @Converter
    class ListConverter<T> : AttributeConverter<List<T>, String> {
        override fun convertToDatabaseColumn(attribute: List<T>): String {
            try {
                return mapper.writeValueAsString(attribute)
            } catch (e: JsonProcessingException) {
                throw IllegalArgumentException(e.message)
            }
        }

        override fun convertToEntityAttribute(dbData: String): List<T>? {
            try {
                return mapper.readValue(dbData)
            } catch (e: IOException) {
                throw IllegalArgumentException(e.message)
            }
        }
    }

    @Converter
    class MapConverter<K, V> : AttributeConverter<Map<K, V>, String> {
        override fun convertToDatabaseColumn(attribute: Map<K, V>): String {
            try {
                return mapper.writeValueAsString(attribute)
            } catch (e: JsonProcessingException) {
                throw IllegalArgumentException()
            }
        }

        override fun convertToEntityAttribute(dbData: String): Map<K, V> {
            try {
                return mapper.readValue(dbData)
            } catch (e: IOException) {
                throw IllegalArgumentException()
            }
        }
    }

    @Converter
    class OpenTimeConverter : AttributeConverter<Map<DayOfWeekUnit, String>, String> {
        override fun convertToDatabaseColumn(attribute: Map<DayOfWeekUnit, String>): String {
            try {
                return mapper.writeValueAsString(attribute)
            } catch (e: JsonProcessingException) {
                throw IllegalArgumentException()
            }
        }

        override fun convertToEntityAttribute(dbData: String): Map<DayOfWeekUnit, String> {
            try {
                return mapper.readValue(dbData, object : TypeReference<Map<DayOfWeekUnit, String>>() {})
            } catch (e: IOException) {
                throw IllegalArgumentException()
            }
        }
    }

    @Converter
    class CakeCustomSketchConverter : AttributeConverter<CakeCustomSketch, String> {
        override fun convertToDatabaseColumn(attribute: CakeCustomSketch): String {
            try {
                return mapper.writeValueAsString(attribute)
            } catch (e: JsonProcessingException) {
                throw IllegalArgumentException()
            }
        }

        override fun convertToEntityAttribute(dbData: String): CakeCustomSketch {
            try {
                return mapper.readValue(dbData, CakeCustomSketch::class.java)
            } catch (e: IOException) {
                throw IllegalArgumentException()
            }
        }
    }

    @Converter
    class OrderOptionsInfoConverter : AttributeConverter<OrderOptionsInfo, String> {
        override fun convertToDatabaseColumn(attribute: OrderOptionsInfo): String {
            try {
                return mapper.writeValueAsString(attribute)
            } catch (e: JsonProcessingException) {
                throw IllegalArgumentException()
            }
        }

        override fun convertToEntityAttribute(dbData: String): OrderOptionsInfo {
            try {
                return mapper.readValue(dbData, OrderOptionsInfo::class.java)
            } catch (e: IOException) {
                throw IllegalArgumentException()
            }
        }

    }
}