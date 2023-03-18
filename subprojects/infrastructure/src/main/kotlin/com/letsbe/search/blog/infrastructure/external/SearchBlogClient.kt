package com.letsbe.search.blog.infrastructure.external

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class SearchBlogClient(
    private val kakaoSearchBlogClient: KakaoSearchBlogClient
) {
    val logger = LoggerFactory.getLogger(this::class.java)

    fun searchBlog(keyword: String): List<BlogPostDTO> {
        val response = kakaoSearchBlogClient.search(
            KakaoSearchBlogRequest(
                query = keyword
            )
        )

//        response.subscribe(System.out::println)
//        val result = response.toFuture().get()
        val result = response.block() // TODO : Netty를 사용못하는데 어떻게 해야할까?, webflux의존성에 대해서 생각해볼 것
        return result?.documents?.map { it.toBlogPostDTO() } ?: emptyList()
    }
}
