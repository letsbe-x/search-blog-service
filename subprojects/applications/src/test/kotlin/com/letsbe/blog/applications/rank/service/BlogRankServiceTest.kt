package com.letsbe.blog.applications.rank.service

import com.letsbe.blog.domain.search.dto.BlogSearchRequestDto
import com.letsbe.blog.domain.search.vo.SearchProviderSpec
import com.letsbe.blog.domain.search.vo.SearchSortBySpec
import com.letsbe.blog.infrastructure.rank.BlogRankClient
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.concurrent.Executors

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [BlogRankService::class])
internal class BlogRankServiceTest {

    @Autowired
    lateinit var blogRankService: BlogRankService

    @MockBean
    lateinit var blogRankClient: BlogRankClient

    @Test
    @Disabled
    fun `updateRank when multiple thread call`() {
        val request = BlogSearchRequestDto(
            query = "test",
            sort = SearchSortBySpec.ACCURACY,
            page = 1,
            size = 10,
            provider = SearchProviderSpec.KAKAO
        )

        val currentRankBoard = blogRankService.getRank()
        val currentRankBoardMap = currentRankBoard.map { it.keyword to it.count }.toMap().toMutableMap()
        val keywordList = currentRankBoard.map { it.keyword }
        val callByKeyword = keywordList.associateWith { 0 }.toMutableMap()

        `when`(blogRankClient.increaseCount(request)).thenAnswer {
            callByKeyword[request.query] = callByKeyword[request.query]!! + 1
            return@thenAnswer Unit
        }

        val executor = Executors.newFixedThreadPool(10)
        for (i in 1..100) {
            keywordList.forEach { keyword ->
                executor.execute {
                    blogRankService.updateRank(request)
                }
                callByKeyword[keyword] = callByKeyword[keyword]!! + 1
            }
        }
        executor.shutdown()

        val newRankBoard = blogRankService.getRank()
        Assertions.assertThat(newRankBoard).hasSize(5)
        newRankBoard.forEach { newRankItem ->
            Assertions.assertThat(newRankItem.count).isEqualTo(callByKeyword[newRankItem.keyword]!! + currentRankBoardMap[newRankItem.keyword]!!)
        }
    }
}
