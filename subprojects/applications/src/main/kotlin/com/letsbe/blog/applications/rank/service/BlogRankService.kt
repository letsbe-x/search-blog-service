package com.letsbe.blog.applications.rank.service

import com.letsbe.blog.applications.rank.dto.BlogRankDto
import com.letsbe.blog.infrastructure.rank.service.RedisService
import org.springframework.stereotype.Service

@Service
class BlogRankService(
    private val redisService: RedisService
) {
    // TODO: Do 사용에 대해서 좀 더 생각해볼것
//    private val blogRankDo = BlogRankDo()

//    fun getRank(): List<BlogRankDto> {
//        return blogRankDo.getRank()
//            .mapIndexed { idx, item ->
//                BlogRankDto(
//                    rank = idx + 1,
//                    keyword = item.keyword,
//                    count = item.requestCount
//                )
//            }
//    }
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
//        blogRankDo.updateRank(keyword)
        redisService.addScore(keyword)
    }
}
