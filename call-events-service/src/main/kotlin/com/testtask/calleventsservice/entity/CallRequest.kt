package com.testtask.calleventsservice.entity

import com.testtask.calleventsservice.converter.JpaConverterJson
import com.testtask.library.events.CallEvent
import org.springframework.http.HttpStatus
import java.time.Clock.systemUTC
import java.time.LocalDateTime
import java.time.ZoneOffset.UTC
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "call_requests")
data class CallRequest(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,
    val url: String,
    @Convert(converter = JpaConverterJson::class)
    val params: Map<String, String>,
    @Enumerated(EnumType.ORDINAL)
    var processingStatus: HttpStatus? = null,
    val timestamp: Long,
) {
    constructor(event: CallEvent, status: HttpStatus? = null) : this(
        url = event.url,
        params = event.params,
        processingStatus = status,
        timestamp = LocalDateTime.now(systemUTC()).toInstant(UTC).toEpochMilli()
    )
}