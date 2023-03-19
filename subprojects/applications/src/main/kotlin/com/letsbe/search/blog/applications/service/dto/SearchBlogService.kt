package com.letsbe.search.blog.applications.service.dto

import com.letsbe.search.blog.applications.dto.BlogPostDto
import com.letsbe.search.blog.infrastructure.external.SearchBlogClient
import org.springframework.stereotype.Service

@Service
class SearchBlogService(
    private val searchBlogClient: SearchBlogClient
) {
    val logger = org.slf4j.LoggerFactory.getLogger(this::class.java)

    suspend fun searchBlog(keyword: String): List<BlogPostDto> {
        val response = searchBlogClient.searchBlog(keyword)

        logger.info("response: {}", response)
        return response.map { BlogPostDto.from(it) }
    }
}
