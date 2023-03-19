package com.letsbe.blog.search.interfaces.configuration

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import java.time.Clock
import java.time.ZoneId

@Configuration
@ComponentScan(
    basePackages = [
        "com.letsbe.blog.search"
    ]
)
@ConfigurationPropertiesScan(
    basePackages = [
        "com.letsbe.blog.search.interfaces.configuration"
    ]
)
class InterfaceConfiguration {
    @Bean
    fun clock(): Clock {
        return Clock.system(ZoneId.of("Asia/Seoul"))
    }
}
