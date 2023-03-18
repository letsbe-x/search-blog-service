package com.letsbe.search.blog.infrasturcture

import com.letsbe.search.blog.infrastructure.configuration.KakaoSearchBlogProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Import

// TODO: Check
@SpringBootApplication
@ConfigurationPropertiesScan("com.letsbe.search.blog.infrasturcture")
@Import(
    value = [
        KakaoSearchBlogProperties::class
    ]
)
class TestApplication
