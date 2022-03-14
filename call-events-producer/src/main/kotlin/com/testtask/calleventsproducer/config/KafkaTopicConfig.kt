package com.testtask.calleventsproducer.config

import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.KafkaAdmin


@Configuration
class KafkaTopicConfig {
    @Value(value = "\${kafka.bootstrapAddress}")
    private val bootstrapAddress: String? = null

    @Value(value = "\${kafka.callEventsTopicName}")
    private val callEventsTopicName: String? = null

    @Bean
    fun kafkaAdmin(): KafkaAdmin {
        val configs: MutableMap<String, Any?> = HashMap()
        configs[AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress
        return KafkaAdmin(configs)
    }

    @Bean
    fun topic1(): NewTopic {
        return NewTopic(callEventsTopicName, 1, 1)
    }
}