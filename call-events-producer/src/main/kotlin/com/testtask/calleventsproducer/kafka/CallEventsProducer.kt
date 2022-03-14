package com.testtask.calleventsproducer.kafka

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.testtask.library.events.CallEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class TestProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
) {

    @Value(value = "\${kafka.callEventsTopicName}")
    private val callEventsTopicName: String = ""
    private var eventsCounter: Int = 0

    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    @Scheduled(fixedDelay = 2000)
    fun produceTestEvents() {
        val event = CallEvent(
            "http://localhost:8080/test",
            mapOf(
                "eventNumber" to "$eventsCounter",
                "param2_$eventsCounter" to "value2_$eventsCounter"
            )
        )

        val result = kafkaTemplate.send(callEventsTopicName, jacksonObjectMapper().writeValueAsString(event)).get()
        log.debug("Send result: $result")
        log.info("Event number $eventsCounter sent. $event")
        eventsCounter++
    }
}