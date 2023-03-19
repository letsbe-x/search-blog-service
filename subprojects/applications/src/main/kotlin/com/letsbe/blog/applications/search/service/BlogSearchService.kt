package com.letsbe.blog.applications.search.service

import com.letsbe.blog.applications.search.dto.BlogPostDto
import com.letsbe.blog.domain.search.aggregates.BlogSearchRequestDo
import com.letsbe.blog.infrastructure.search.external.BlogSearchClient
import org.slf4j.Logger
import org.springframework.stereotype.Service

@Service
class BlogSearchService(
    private val blogSearchClient: BlogSearchClient
) {
    val logger: Logger = org.slf4j.LoggerFactory.getLogger(this::class.java)

    suspend fun blogSearch(keyword: String, sort: String, page: Int, size: Int): List<BlogPostDto> {
        val request = BlogSearchRequestDo(keyword, sort, page, size).toDto()
        val response = blogSearchClient.search(request).toDtoList()

        logger.info("response: {}", response)
        return response.map { BlogPostDto.from(it) }
    }
}
