package com.letsbe.search.blog.applications.service

import com.letsbe.search.blog.applications.blog.BlogPostDO
import com.letsbe.search.blog.infrastructure.external.SearchBlogClient
import org.springframework.context.annotation.ComponentScan
import org.springframework.stereotype.Service

@Service
@ComponentScan(
    basePackages = [
        "com.letsbe.search.blog.infrastructures.main"
    ]
)
class SearchBlogService(
    private val searchBlogClient: SearchBlogClient
) {
    fun searchBlog(keyword: String): List<BlogPostDO> {
        val response = searchBlogClient.searchBlog(keyword)
        return response.map { BlogPostDO.from(it) }
    }
}
