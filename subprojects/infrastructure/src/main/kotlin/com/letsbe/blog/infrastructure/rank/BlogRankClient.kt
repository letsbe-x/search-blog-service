package com.letsbe.blog.infrastructure.rank

import com.letsbe.blog.domain.rank.dto.BlogRankItemDto
import com.letsbe.blog.infrastructure.rank.entity.BlogRequestEntity
import com.letsbe.blog.infrastructure.rank.repository.BlogRankItemRepository
import com.letsbe.blog.infrastructure.rank.service.RedisService
import org.springframework.stereotype.Service

@Service
class BlogRankClient(
    private val redisService: RedisService,
    private val blogRankItemRepository: BlogRankItemRepository
) {
    // TODO: Do 사용에 대해서 좀 더 생각해볼것
    // TODO: DO로직 대해서는 0.11.x JPA를 사용하면서 다시 생각해보자
    fun getBlogRank(): List<BlogRankItemDto> {
//        return redisService.getRankingItemList()
        return blogRankItemRepository.findTop10ByOrderByCountDesc()
            .map {
                BlogRankItemDto(it.keyword, it.count)
            }
    }

    fun updateRank(keyword: String) {
        // TODO: DO로직 대해서는 0.11.x JPA를 사용하면서 다시 생각해보자
        redisService.addScore(keyword)
        blogRankItemRepository.getBlogRequestEntityByKeyword(keyword)?.let {
            blogRankItemRepository.save(it.copy(count = it.count + 1))
        } ?: run {
            blogRankItemRepository.save(BlogRequestEntity(keyword = keyword, count = 1))
        }
    }
}
