package com.letsbe.blog.search.infrastructure.external

import com.letsbe.blog.search.domain.aggregates.BlogSearchResultListDo
import com.letsbe.blog.search.domain.dto.BlogSearchRequestDto
import com.letsbe.blog.search.infrastructure.external.kakao.KakaoBlogSearchClient
import com.letsbe.blog.search.infrastructure.external.kakao.KakaoBlogSearchRequest
import kotlinx.coroutines.reactive.awaitSingle
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class BlogSearchClient(
    private val kakaoBlogSearchClient: KakaoBlogSearchClient
) {
    val logger: Logger = LoggerFactory.getLogger(this::class.java)

    suspend fun searchBlog(request: BlogSearchRequestDto): BlogSearchResultListDo {
        val response = kakaoBlogSearchClient.search(
            KakaoBlogSearchRequest(
                query = request.query,
                sort = request.sort.kakao,
                page = request.page,
                size = request.size
            )
        ).awaitSingle()

        logger.info("kakaoResponse: {}", response)
        // TODO: reactor를 사용하여 webflux답게 변경하는 방법을 생각해볼것
        return BlogSearchResultListDo(
            result = response?.toBlogSearchResultDtoList() ?: listOf()
        )
    }
}
