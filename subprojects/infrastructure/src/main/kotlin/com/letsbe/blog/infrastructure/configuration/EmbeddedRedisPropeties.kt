// package com.letsbe.blog.infrastructure.configuration
//
// import jakarta.annotation.PostConstruct
// import jakarta.annotation.PreDestroy
// import org.springframework.beans.factory.annotation.Value
// import org.springframework.context.annotation.Configuration
// import redis.embedded.RedisServer
// import java.io.IOException
// import kotlin.properties.Delegates
//
//
// @Configuration
// class EmbeddedRedisPropeties {
//    // TODO: @value -> to ConfigurationProperties
//    // TODO: Project Configuration
//    @get:Value("\${spring.redis.data.port}")
//    private var redisPort by Delegates.notNull<Int>()
//    private lateinit var redisServer: RedisServer
//
//    @PostConstruct
//    @Throws(IOException::class)
//    fun redisServer() {
//        redisServer = RedisServer(redisPort)
//        redisServer.start()
//    }
//
//    @PreDestroy
//    fun stopRedis() {
//        redisServer.stop()
//    }
// }
