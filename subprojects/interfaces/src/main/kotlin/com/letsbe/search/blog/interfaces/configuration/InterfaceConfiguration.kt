package com.letsbe.search.blog.interfaces.configuration

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import java.time.Clock
import java.time.ZoneId

@Configuration
@ComponentScan(
    basePackages = [
        "com.letsbe.search.blog"
    ]
)
@ConfigurationPropertiesScan(
    basePackages = [
        "com.letsbe.search.blog.interfaces.configuration"
    ]
)
class InterfaceConfiguration {
    @Bean
    fun clock(): Clock {
        return Clock.system(ZoneId.of("Asia/Seoul"))
    }
}
