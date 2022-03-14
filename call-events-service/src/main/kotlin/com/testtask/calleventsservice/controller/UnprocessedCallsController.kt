package com.testtask.calleventsservice.controller

import com.testtask.calleventsservice.entity.CallRequest
import com.testtask.calleventsservice.service.CallEventsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class UnprocessedCallsController(
    val eventsService: CallEventsService
) {

    @GetMapping("/info/unprocessed")
    fun getUnprocessed(): List<CallRequest> {
        return eventsService.getUnprocessed()
    }

    @GetMapping("/test")
    fun testEndpointForCallRequestProcessing() {

    }
}