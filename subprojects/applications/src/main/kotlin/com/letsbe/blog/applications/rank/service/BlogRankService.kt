package com.letsbe.blog.applications.rank.service

import com.letsbe.blog.applications.rank.dto.BlogRankDto
import com.letsbe.blog.domain.search.dto.BlogSearchRequestDto
import com.letsbe.blog.infrastructure.rank.BlogRankClient
import org.springframework.stereotype.Service

@Service
class BlogRankService(
    private val blogRankClient: BlogRankClient
) {
    fun getRank(): List<BlogRankDto> {
        val blogRank = blogRankClient.getBlogRequestRank()
        return blogRank.mapIndexed { index, blogRankItemDto ->
            BlogRankDto(
                rank = index + 1,
                keyword = blogRankItemDto.keyword,
                count = blogRankItemDto.requestCount
            )
        }
    }

    fun updateRank(request: BlogSearchRequestDto) {
        blogRankClient.increaseCount(request)
    }
}
