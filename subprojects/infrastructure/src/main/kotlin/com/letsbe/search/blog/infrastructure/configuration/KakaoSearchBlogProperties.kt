package com.letsbe.search.blog.infrastructure.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
data class KakaoSearchBlogProperties(
    // TODO: @value -> to ConfigurationProperties
    // TODO: Project Configuration
    @Value("\${kakao.rest-api.blog.baseurl}")
    val baseUrl: String,
    @Value("\${kakao.rest-api.blog.token}")
    val token: String
)
