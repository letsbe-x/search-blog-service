package com.letsbe.blog.infrastructure.rank.service

import com.letsbe.blog.domain.rank.dto.BlogRankItemDto
import com.letsbe.blog.domain.rank.entity.RankItemEntity
import com.letsbe.blog.domain.rank.entity.RankItemEntityId
import com.letsbe.blog.infrastructure.configuration.RedisProperties
import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.slf4j.LoggerFactory
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.springframework.stereotype.Repository
import redis.embedded.RedisServer
import java.io.IOException

@Repository
@ConfigurationPropertiesScan(
    basePackages = [
        "com.letsbe.blog.search.infrastructure.configuration"
    ]
)
@EnableRedisRepositories
class RedisService(
    redisProperties: RedisProperties
) {
    private val redisHost: String = redisProperties.host
    private val redisPort: Int = redisProperties.port

    private val logger = LoggerFactory.getLogger(this::class.java)

//    @Resource(name = "redisTemplate")
//    private lateinit var redisTemplate: RedisTemplate<String, RankItemEntityId>

    // TODO: README 초기화 읽어볼것
    fun redisTemplate(): RedisTemplate<String, RankItemEntityId> {
        val redisTemplate = RedisTemplate<String, RankItemEntityId>()
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.valueSerializer = StringRedisSerializer() // RankItemEntityId는 String으로 저장

        val lettuceConnectionFactory = LettuceConnectionFactory(redisHost, redisPort)
        lettuceConnectionFactory.afterPropertiesSet()

        redisTemplate.setConnectionFactory(lettuceConnectionFactory)
        redisTemplate.afterPropertiesSet()
        return redisTemplate
    }

    fun addScore(keyworkd: RankItemEntityId) = addScore("ranking", keyworkd, 1)

    private fun addScore(key: String, keyword: RankItemEntityId, score: Int) {
        val redisSortedSet = redisTemplate().opsForZSet()
//        val redisSortedSet = redisTemplate.opsForZSet()
        redisSortedSet.incrementScore(key, keyword, score.toDouble())
    }

    fun getRankingItemList(): List<BlogRankItemDto> {
        val redisSortedSet = redisTemplate().opsForZSet()
//        val redisSortedSet = redisTemplate.opsForZSet()

        if (redisSortedSet.size("ranking") == 0L) {
            return emptyList()
        }

        val tuple = redisSortedSet.reverseRangeWithScores("ranking", 0, 9) ?: emptySet()
        logger.info("tuple: $tuple")

        val listEntity = tuple.map {
            RankItemEntity(
                it.value as RankItemEntityId,
                it.score?.toInt() ?: 0 // TODO: 0이면 에러
            )
        }

        return listEntity.map {
            BlogRankItemDto(
                keyword = it.keyword,
                requestCount = it.requestCount
            )
        }
    }

    private lateinit var redisServer: RedisServer

    @PostConstruct
    @Throws(IOException::class)
    fun startServer() {
        redisServer = RedisServer(redisPort)
        logger.info("redisServer active? : ${redisServer.isActive}")
        redisServer.start()
        logger.info("redisServer active? : ${redisServer.isActive}")
    }

    @PreDestroy
    fun stopRedis() {
        redisServer.stop()
    }
}
