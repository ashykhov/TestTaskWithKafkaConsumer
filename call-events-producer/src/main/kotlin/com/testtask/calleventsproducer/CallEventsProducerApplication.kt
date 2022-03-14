package com.testtask.calleventsproducer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class CallEventsProducerApplication

fun main(args: Array<String>) {
    runApplication<CallEventsProducerApplication>(*args)
}
