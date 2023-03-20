package com.letsbe.blog.applications.search.service

import com.letsbe.blog.applications.rank.service.BlogRankService
import com.letsbe.blog.applications.search.dto.BlogPostDto
import com.letsbe.blog.domain.search.aggregates.BlogSearchRequestDo
import com.letsbe.blog.infrastructure.search.external.BlogSearchClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class BlogSearchService(
    private val blogSearchClient: BlogSearchClient,
    private val blogRankService: BlogRankService
) {
    val logger: Logger = LoggerFactory.getLogger(this::class.java)

    // TODO: 로직이 BlogSearchDO로 이동되어야 함
    suspend fun blogSearch(keyword: String, sort: String, page: Int, size: Int): List<BlogPostDto> {
        val request = BlogSearchRequestDo(keyword, sort, page, size).toDto()

        logger.info("request: {}", request)
        logger.info("Thread: {}", Thread.currentThread().name)

        // TODO: 서비스 연계는 DO에서 관리해야합니다. / 서비스 연계는 비즈니스 로직이 아닙니다.
        withContext(Dispatchers.IO) {
            logger.info("keyword: {}", keyword)
            logger.info("Thread IO: {}", Thread.currentThread().name)
            blogRankService.updateRank(keyword)
        }

        val kakaoResponse = blogSearchClient.kakaoSearch(request).toDtoList()
        logger.info("kakaoResponse: {}", kakaoResponse)
        val naverResponse = blogSearchClient.naverSearch(request).toDtoList()
        logger.info("naverResponse: {}", naverResponse)

        val response = kakaoResponse + naverResponse
        return response.map { BlogPostDto.from(it) }
    }
}
