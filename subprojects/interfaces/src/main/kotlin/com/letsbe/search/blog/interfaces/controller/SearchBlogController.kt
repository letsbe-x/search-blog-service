package com.letsbe.search.blog.interfaces.controller

import com.letsbe.search.blog.applications.service.dto.SearchBlogService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class SearchBlogController(
    private val searchBlogService: SearchBlogService
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/search/blog")
    suspend fun searchBlog(
        @RequestParam(required = true) query: String,
        @RequestParam(required = false, defaultValue = "accuracy") sort: String
    ): Mono<SearchBlogResponse> {
        val response = searchBlogService.searchBlog(query, sort)

        logger.info("response: {}", response)
        return Mono.just(SearchBlogResponse(response))
    }
}
