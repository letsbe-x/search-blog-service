package com.letsbe.search.blog.applications.service

import com.letsbe.search.blog.applications.blog.BlogPostDO
import com.letsbe.search.blog.infrastructure.external.SearchBlogClient
import org.springframework.stereotype.Service

@Service
class SearchBlogService(
    private val searchBlogClient: SearchBlogClient
) {
    val logger = org.slf4j.LoggerFactory.getLogger(this::class.java)

    suspend fun searchBlog(keyword: String): List<BlogPostDO> {
        val response = searchBlogClient.searchBlog(keyword)

        logger.info("response: {}", response)
        return response.map { BlogPostDO.from(it) }
    }
}
