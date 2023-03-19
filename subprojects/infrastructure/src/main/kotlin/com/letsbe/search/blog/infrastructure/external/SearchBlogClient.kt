package com.letsbe.search.blog.infrastructure.external

import kotlinx.coroutines.reactive.awaitSingle
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class SearchBlogClient(
    private val kakaoSearchBlogClient: KakaoSearchBlogClient
) {
    val logger = LoggerFactory.getLogger(this::class.java)

    suspend fun searchBlog(keyword: String): List<BlogPostDTO> {
        val response = kakaoSearchBlogClient.search(
            KakaoSearchBlogRequest(
                query = keyword
            )
        )

        logger.info("kakaoResponse: {}", response)
        // TODO: reactor를 사용하여 webflux답게 변경하는 방법을 생각해볼것
        return response.awaitSingle()?.documents?.map { it.toBlogPostDTO() } ?: listOf()
    }
}
