package com.letsbe.search.blog.infrastructure.external

import com.letsbe.search.blog.domain.aggregates.SearchBlogResultListDo
import com.letsbe.search.blog.domain.dto.SearchBlogRequestDto
import com.letsbe.search.blog.infrastructure.external.kakao.KakaoSearchBlogClient
import com.letsbe.search.blog.infrastructure.external.kakao.KakaoSearchBlogRequest
import kotlinx.coroutines.reactive.awaitSingle
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class SearchBlogClient(
    private val kakaoSearchBlogClient: KakaoSearchBlogClient
) {
    val logger: Logger = LoggerFactory.getLogger(this::class.java)

    suspend fun searchBlog(request: SearchBlogRequestDto): SearchBlogResultListDo {
        val response = kakaoSearchBlogClient.search(
            KakaoSearchBlogRequest(
                query = request.query,
                sort = request.sort.kakao,
                page = request.page,
                size = request.size
            )
        ).awaitSingle()

        logger.info("kakaoResponse: {}", response)
        // TODO: reactor를 사용하여 webflux답게 변경하는 방법을 생각해볼것
        return SearchBlogResultListDo(
            result = response?.toSearchBlogResultDtoList() ?: listOf()
        )
    }
}
