package com.letsbe.blog.applications.rank.service

import com.letsbe.blog.applications.rank.dto.BlogRankDto
import com.letsbe.blog.domain.rank.dto.BlogRankItemDto
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentSkipListSet

@Service
class BlogRankService {

    // TODO: 레디스 Sortedset 붙이기 전에 돌아가게 만들어보자
    private val rankDB: ConcurrentSkipListSet<BlogRankItemDto> = ConcurrentSkipListSet(
        compareByDescending<BlogRankItemDto> { it.count }
    )

    fun getRank(): List<BlogRankDto> {
        return rankDB.take(10)
            .mapIndexed { idx, item ->
                BlogRankDto(
                    rank = idx + 1,
                    keyword = item.keyword,
                    count = item.count
                )
            }
    }

    fun updateRank(keyword: String) {
        val item = rankDB.find { it.keyword == keyword }
        if (item != null) {
            rankDB.remove(item)
            rankDB.add(item.copy(count = item.count + 1))
        } else {
            rankDB.add(BlogRankItemDto(keyword = keyword, count = 1))
        }
    }

    init {
        val sampleList = listOf(
            BlogRankItemDto(
                keyword = "테스트",
                count = 100
            ),
            BlogRankItemDto(
                keyword = "테스트2",
                count = 50
            ),
            BlogRankItemDto(
                keyword = "테스트3",
                count = 30
            ),
            BlogRankItemDto(
                keyword = "테스트4",
                count = 20
            ),
            BlogRankItemDto(
                keyword = "테스트5",
                count = 10
            )
        )
        rankDB.addAll(sampleList)
    }
}
