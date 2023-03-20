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

    suspend fun blogSearch(keyword: String, sort: String, page: Int, size: Int): List<BlogPostDto> {
        val request = BlogSearchRequestDo(keyword, sort, page, size).toDto()

        logger.info("request: {}", request)
        logger.info("Thread: {}", Thread.currentThread().name)

        withContext(Dispatchers.IO) {
            logger.info("keyword: {}", keyword)
            logger.info("Thread IO: {}", Thread.currentThread().name)
            blogRankService.updateRank(keyword)
        }

        val response = blogSearchClient.search(request).toDtoList()

        logger.info("response: {}", response)
        return response.map { BlogPostDto.from(it) }
    }
}