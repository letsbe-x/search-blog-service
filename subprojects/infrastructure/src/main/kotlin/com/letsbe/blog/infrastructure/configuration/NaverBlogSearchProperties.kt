package com.letsbe.blog.infrastructure.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
data class NaverBlogSearchProperties(
    // TODO: @value -> to ConfigurationProperties
    // TODO: Project Configuration
    @Value("\${naver.rest-api.blog.baseurl}")
    val baseUrl: String,
    @Value("\${naver.rest-api.blog.clientid}")
    val clientId: String,
    @Value("\${naver.rest-api.blog.clientsecret}")
    val clientSecret: String
)
