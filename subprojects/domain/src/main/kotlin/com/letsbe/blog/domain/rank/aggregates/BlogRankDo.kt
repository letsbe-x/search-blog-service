package com.letsbe.blog.domain.rank.aggregates

import com.letsbe.blog.domain.rank.dto.BlogRankItemDto
import java.util.concurrent.ConcurrentSkipListSet

class BlogRankDo {

    fun getRank(): List<BlogRankItemDto> {
        return rankDB.take(MAX_RANK_COUNT)
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

    // TODO: 레디스 Sortedset 붙이기 전에 돌아가게 만들어보자
    private val rankDB: ConcurrentSkipListSet<BlogRankItemDto> = ConcurrentSkipListSet(
        compareByDescending<BlogRankItemDto> { it.count }
    )

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

    companion object {
        const val MAX_RANK_COUNT = 10
    }
}
