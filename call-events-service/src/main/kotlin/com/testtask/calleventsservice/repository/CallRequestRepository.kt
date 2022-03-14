package com.testtask.calleventsservice.repository

import com.testtask.calleventsservice.entity.CallRequest
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CallRequestRepository : CrudRepository<CallRequest, Long> {
    fun save(request: CallRequest)
    fun getAllByProcessingStatusIsNullOrderByTimestampDesc(): List<CallRequest>
}