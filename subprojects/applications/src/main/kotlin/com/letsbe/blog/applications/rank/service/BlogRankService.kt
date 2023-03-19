package com.letsbe.blog.applications.rank.service

import com.letsbe.blog.applications.rank.dto.BlogRankDto
import org.springframework.stereotype.Service

@Service
class BlogRankService {
    fun getRank(): List<BlogRankDto> {
        return listOf(
            BlogRankDto(
                rank = 1,
                keyword = "테스트",
                count = 100
            ),
            BlogRankDto(
                rank = 2,
                keyword = "테스트2",
                count = 50
            ),
            BlogRankDto(
                rank = 3,
                keyword = "테스트3",
                count = 30
            ),
            BlogRankDto(
                rank = 4,
                keyword = "테스트4",
                count = 20
            ),
            BlogRankDto(
                rank = 5,
                keyword = "테스트5",
                count = 10
            )
        )
    }
}
