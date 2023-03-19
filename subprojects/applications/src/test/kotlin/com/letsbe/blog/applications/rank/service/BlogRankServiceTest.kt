package com.letsbe.blog.applications.rank.service

import com.letsbe.blog.applications.ApplicationsAbstractServiceTest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.util.concurrent.Executors

internal class BlogRankServiceTest : ApplicationsAbstractServiceTest() {

    @Autowired
    lateinit var blogRankService: BlogRankService

    @Test
    fun getRank() {
    }

    @Test
    fun `updateRank when multiple thread call`() {
        val currentRankBoard = blogRankService.getRank()
        val currentRankBoardMap = currentRankBoard.associateBy { it.keyword }
        val keywordList = currentRankBoard.map { it.keyword }
        val callByKeyword = keywordList.associateWith { 0 }.toMutableMap()

        val executor = Executors.newFixedThreadPool(10)
        for (i in 1..100) {
            keywordList.forEach { keyword ->
                executor.execute {
                    blogRankService.updateRank(keyword)
                }
                callByKeyword[keyword] = callByKeyword[keyword]!! + 1
            }
        }
        executor.shutdown()

        val newRankBoard = blogRankService.getRank()
        Assertions.assertThat(newRankBoard).hasSize(5)
        newRankBoard.forEach { newRankItem ->
            Assertions.assertThat(newRankItem.count).isEqualTo(callByKeyword[newRankItem.keyword]!! + currentRankBoardMap[newRankItem.keyword]!!.count)
        }
    }
}
