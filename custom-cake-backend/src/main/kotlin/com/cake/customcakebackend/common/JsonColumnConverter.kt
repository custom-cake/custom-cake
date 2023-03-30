package com.cake.customcakebackend.common

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.IOException
import javax.persistence.AttributeConverter
import javax.persistence.Converter

private val mapper: ObjectMapper = ObjectMapper()
class JsonColumnConverter {

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
}