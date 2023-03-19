package com.letsbe.blog.search.applications.service

import com.letsbe.blog.search.applications.dto.BlogPostDto
import com.letsbe.blog.search.domain.aggregates.SearchBlogRequestDo
import com.letsbe.blog.search.infrastructure.external.SearchBlogClient
import org.slf4j.Logger
import org.springframework.stereotype.Service

@Service
class SearchBlogService(
    private val searchBlogClient: SearchBlogClient
) {
    val logger: Logger = org.slf4j.LoggerFactory.getLogger(this::class.java)

    suspend fun searchBlog(keyword: String, sort: String, page: Int, size: Int): List<BlogPostDto> {
        val request = SearchBlogRequestDo(keyword, sort, page, size).toDto()
        val response = searchBlogClient.searchBlog(request).toDtoList()

        logger.info("response: {}", response)
        return response.map { BlogPostDto.from(it) }
    }
}
