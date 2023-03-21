package com.letsbe.blog.infrastructure.rank

import com.letsbe.blog.domain.rank.dto.BlogRankItemDto
import com.letsbe.blog.domain.search.dto.BlogSearchRequestDto
import com.letsbe.blog.infrastructure.rank.entity.BlogRequestHistoryEntity
import com.letsbe.blog.infrastructure.rank.repository.BlogRequestHistoryInfoRepository
import com.letsbe.blog.infrastructure.rank.repository.RedisRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class BlogRankClient(
    private val redisRepository: RedisRepository,
    private val blogRequestHistoryRpeository: BlogRequestHistoryInfoRepository
) {
    private val logger = LoggerFactory.getLogger(this::class.java)
    fun getBlogRank(): List<BlogRankItemDto> {
        logger.info("Redis : {}", redisRepository.getRankingItemList())
        logger.info(
            "DBHistroyRepository : {}",
            blogRequestHistoryRpeository.getAllByBlogRequestItem()
                .map {
                    BlogRankItemDto(it.getKeyword(), it.getCount())
                }
        )

        return blogRequestHistoryRpeository.getAllByBlogRequestItem()
            .map {
                BlogRankItemDto(it.getKeyword(), it.getCount())
            }
    }

    fun increaseCount(request: BlogSearchRequestDto) {
        redisRepository.addScore(request.query)
        blogRequestHistoryRpeository.save(BlogRequestHistoryEntity(keyword = request.query, provider = request.provider.alias))
    }
}
