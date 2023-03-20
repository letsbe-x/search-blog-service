package com.letsbe.blog.applications.rank.service

import com.letsbe.blog.applications.rank.dto.BlogRankDto
import com.letsbe.blog.infrastructure.rank.service.RedisService
import org.springframework.stereotype.Service

@Service
class BlogRankService(
    private val redisService: RedisService
) {
    // TODO: Do 사용에 대해서 좀 더 생각해볼것
    // TODO: DO로직 대해서는 0.11.x JPA를 사용하면서 다시 생각해보자
    fun getRank(): List<BlogRankDto> {
        return redisService.getRankingItemList()
            .mapIndexed() { idx, item ->
                BlogRankDto(
                    rank = idx + 1,
                    keyword = item.keyword,
                    count = item.requestCount
                )
            }
    }

    fun updateRank(keyword: String) {
        // TODO: DO로직 대해서는 0.11.x JPA를 사용하면서 다시 생각해보자
//        blogRankDo.updateRank(keyword)
        redisService.addScore(keyword)
    }
}
