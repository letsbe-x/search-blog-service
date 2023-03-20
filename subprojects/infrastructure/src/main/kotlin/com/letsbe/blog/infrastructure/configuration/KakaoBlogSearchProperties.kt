package com.letsbe.blog.infrastructure.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
data class KakaoBlogSearchProperties(
    // TODO: @value -> to ConfigurationProperties
    // TODO: Project Configuration
    @Value("\${kakao.rest-api.blog.baseurl}")
    val baseUrl: String,
    @Value("\${kakao.rest-api.blog.token}")
    val token: String
)
