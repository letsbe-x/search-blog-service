package com.letsbe.search.blog.interfaces.controller

import com.letsbe.search.blog.applications.blog.BlogPostDO
import com.letsbe.search.blog.applications.service.SearchBlogService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class SearchBlogController(
    private val searchBlogService: SearchBlogService
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/search/blog")
    suspend fun searchBlog(request: SearchBlogRequest): Mono<SearchBlogResponse> {
        logger.info("request: {}", request)

        val response = searchBlogService.searchBlog(request.query)

        logger.info("response: {}", response)
        return Mono.just(SearchBlogResponse(response))
    }
}

data class SearchBlogRequest(
    val query: String
)

data class SearchBlogResponse(
    val blogPosts: List<BlogPostDO>
)
