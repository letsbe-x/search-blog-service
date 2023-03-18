package com.letsbe.search.blog.interfaces

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan("com.letsbe.search.blog.interfaces.configuration")
class ServerApplication

fun main(args: Array<String>) {
    runApplication<ServerApplication>(*args)
}
