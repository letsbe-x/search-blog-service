package com.letsbe.blog.infrasturcture.search

import com.letsbe.blog.infrastructure.configuration.KakaoBlogSearchProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Import

// TODO: Check
@SpringBootApplication
@ConfigurationPropertiesScan("com.letsbe.blog.infrasturcture")
@Import(
    value = [
        KakaoBlogSearchProperties::class
    ]
)
class TestApplication
