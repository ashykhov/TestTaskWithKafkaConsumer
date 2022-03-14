package com.testtask.calleventsservice.converter

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.IOException
import javax.persistence.AttributeConverter
import javax.persistence.Converter


@Converter(autoApply = true)
class JpaConverterJson : AttributeConverter<Any?, String?> {
    override fun convertToDatabaseColumn(meta: Any?): String? {
        return try {
            jacksonObjectMapper().writeValueAsString(meta)
        } catch (ex: JsonProcessingException) {
            null
        }
    }

    override fun convertToEntityAttribute(dbData: String?): Any? {
        return try {
            jacksonObjectMapper().readValue(dbData, Any::class.java)
        } catch (ex: IOException) {
            null
        }
    }
}