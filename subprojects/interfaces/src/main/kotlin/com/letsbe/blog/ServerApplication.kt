package com.letsbe.blog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan("com.letsbe.blog.interfaces.configuration")
class ServerApplication

fun main(args: Array<String>) {
    runApplication<ServerApplication>(*args)
}
