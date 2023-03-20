package com.letsbe.blog.infrastructure.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
data class RedisProperties(
    // TODO: @value -> to ConfigurationProperties
    // TODO: Project Configuration
    @Value("\${spring.data.redis.host}")
    val host: String,
    @Value("\${spring.data.redis.port}")
    val port: Int
)
