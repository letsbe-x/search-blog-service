package com.letsbe.blog.interfaces.search.controller

import com.letsbe.blog.applications.search.dto.BlogPostDto
import com.letsbe.blog.applications.search.service.BlogSearchService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
class BlogSearchController(
    private val blogSearchService: BlogSearchService
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("blog/search")
    suspend fun blogSearch(
        // TODO: @ModelAttribute를 사용하여, RequestParams를 DTO로 변환하여 사용하는 방법도 있음
        @RequestParam(required = true) query: String,
        @RequestParam(required = false, defaultValue = "accuracy") sort: String,
        // TODO: 오프셋을 어떻게 지정할지 고민해봐야함.. 우선은 request를 그대로 사용한다.
        // TODO: Pageable을 사용하는 것도 생각, 검색조건이 바뀌는경우에는 초기화도 해줘야함!
        @RequestParam(required = false, defaultValue = "1") page: Int,
        @RequestParam(required = false, defaultValue = "10") size: Int,
        @RequestParam(required = false, defaultValue = "kakao") provider: String
    ): Flux<BlogPostDto> {
        return blogSearchService.blogSearch(query, sort, page, size, provider)
    }
}
