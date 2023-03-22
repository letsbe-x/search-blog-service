package com.letsbe.blog.infrastructure.rank

import com.letsbe.blog.domain.rank.dto.BlogRankItemDto
import com.letsbe.blog.domain.search.dto.BlogSearchRequestDto
import com.letsbe.blog.infrastructure.rank.entity.BlogRequestHistoryEntity
import com.letsbe.blog.infrastructure.rank.repository.BlogRequestHistoryInfoRepository
import com.letsbe.blog.infrastructure.rank.repository.RedisRepository
import org.slf4j.LoggerFactory
import org.springframework.data.redis.RedisConnectionFailureException
import org.springframework.stereotype.Service

@Service
class BlogRankClient(
    private val redisRepository: RedisRepository,
    private val blogRequestHistoryInfoRepository: BlogRequestHistoryInfoRepository
) {
    private val logger = LoggerFactory.getLogger(this::class.java)
    fun getBlogRequestRank(): List<BlogRankItemDto> {
        return try {
            redisRepository.getBlogRequestItem()
        } catch (e: RedisConnectionFailureException) {
            logger.error("redis connection error: {}", e.message)
            val totalList = blogRequestHistoryInfoRepository.getAllByBlogRequestItem().map { blogRequestItem -> BlogRankItemDto(blogRequestItem.getKeyword(), blogRequestItem.getCount()) }
            redisRepository.initializeRankStore(totalList)
            totalList
        }
    }

    fun increaseCount(request: BlogSearchRequestDto) {
        blogRequestHistoryInfoRepository.save(BlogRequestHistoryEntity(keyword = request.query, provider = request.provider.alias))
        try {
            redisRepository.increaseRequestCounter(request.query)
        } catch (e: RedisConnectionFailureException) {
            logger.error("redis connection error: {}", e.message)
            val totalList = blogRequestHistoryInfoRepository.getAllByBlogRequestItem().map { blogRequestItem -> BlogRankItemDto(blogRequestItem.getKeyword(), blogRequestItem.getCount()) }
            redisRepository.initializeRankStore(totalList)
        }
    }
}
