package com.letsbe.blog.interfaces.rank.controller

import com.letsbe.blog.applications.rank.service.BlogRankService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class BlogRankController(
    private val blogRankService: BlogRankService
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping("blog/rank")
    suspend fun getBlogRank(): Mono<BlogRankResponse> {
        val response = blogRankService.getRank()

        logger.info("response: {}", response)
        return Mono.just(BlogRankResponse(response))
    }
}
