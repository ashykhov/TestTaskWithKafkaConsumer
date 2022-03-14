package com.testtask.calleventsservice.service

import com.testtask.calleventsservice.entity.CallRequest
import com.testtask.calleventsservice.repository.CallRequestRepository
import com.testtask.library.events.CallEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient


@Service
class CallEventsService(
    val callRequestRepo: CallRequestRepository
) {
    private var client = WebClient.builder().build()
    private val log: Logger = LoggerFactory.getLogger(this::class.java)


    @Scheduled(fixedDelay = 20000)
    fun processEvents() {
        getUnprocessed()
            .map { callEntity ->
                val response =
                    client.get()
                        .uri(callEntity.url)
                        .attributes { callEntity.params }
                        .retrieve()
                        .toBodilessEntity()
                        .onErrorReturn(ResponseEntity(HttpStatus.NOT_ACCEPTABLE))
                        .block()
                callEntity.processingStatus = response?.statusCode
                save(callEntity)

                log.info("Event processed: $callEntity")
            }
    }

    fun getUnprocessed(): List<CallRequest> {
        return callRequestRepo.getAllByProcessingStatusIsNullOrderByTimestampDesc()
    }

    fun save(event: CallEvent) {
        save(CallRequest(event))
        // TODO делать аскноледж только после сейва в базу
    }

    private fun save(call: CallRequest) {
        callRequestRepo.save(call)
        // TODO делать аскноледж только после сейва в базу
    }
}