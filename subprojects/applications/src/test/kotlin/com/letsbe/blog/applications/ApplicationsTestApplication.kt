package com.letsbe.blog.applications

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan

// TODO: Check
@SpringBootApplication
@ConfigurationPropertiesScan(
    "com.letsbe.blog.applications"
)
class ApplicationsTestApplication
