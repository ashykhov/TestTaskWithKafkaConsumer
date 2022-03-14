package com.testtask.calleventsservice.kafka

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.testtask.calleventsservice.service.CallEventsService
import com.testtask.library.events.CallEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service


@Service
class CallEventsConsumer(
    val callEventsService: CallEventsService
) {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    @KafkaListener(topics = ["events.TestEventV1"])
    fun listenCallEvents(eventJson: String) {
        val event = readCallEvent(eventJson)
        if (event != null) {
            log.info("Consumed event $event")
            callEventsService.save(event)
        }
    }

    private fun readCallEvent(eventJson: String) = try {
        jacksonObjectMapper().readValue<CallEvent>(eventJson)
    } catch (e: Exception) {
        log.error("Can't read event from json: $eventJson")
        null
    }
}