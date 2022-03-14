package com.testtask.library.events

data class CallEvent(
    val url: String,
    val params: Map<String, String>,
)