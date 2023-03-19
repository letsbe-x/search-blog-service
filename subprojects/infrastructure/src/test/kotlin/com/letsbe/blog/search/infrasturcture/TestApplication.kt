package com.letsbe.blog.search.infrasturcture

import com.letsbe.blog.search.infrastructure.configuration.KakaoSearchBlogProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Import

// TODO: Check
@SpringBootApplication
@ConfigurationPropertiesScan("com.letsbe.blog.search.infrasturcture")
@Import(
    value = [
        KakaoSearchBlogProperties::class
    ]
)
class TestApplication
