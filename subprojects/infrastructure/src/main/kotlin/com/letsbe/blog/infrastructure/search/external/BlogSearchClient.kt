package com.letsbe.blog.infrastructure.search.external

import com.letsbe.blog.domain.search.aggregates.BlogSearchResultListDo
import com.letsbe.blog.domain.search.dto.BlogSearchRequestDto
import com.letsbe.blog.infrastructure.search.external.kakao.KakaoBlogSearchClient
import com.letsbe.blog.infrastructure.search.external.kakao.KakaoBlogSearchRequest
import com.letsbe.blog.infrastructure.search.external.naver.NaverBlogSearchClient
import com.letsbe.blog.infrastructure.search.external.naver.NaverBlogSearchRequest
import kotlinx.coroutines.reactive.awaitSingle
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class BlogSearchClient(
    private val kakaoBlogSearchClient: KakaoBlogSearchClient,
    private val naverBlogSearchClient: NaverBlogSearchClient,
) {
    val logger: Logger = LoggerFactory.getLogger(this::class.java)

    suspend fun kakaoSearch(request: BlogSearchRequestDto): BlogSearchResultListDo {
        val response = kakaoBlogSearchClient.search(
            KakaoBlogSearchRequest(
                query = request.query,
                sort = request.sort.kakao, // TODO: 좀더 Provier별로 분리할 필요가 있음
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

    suspend fun naverSearch(request: BlogSearchRequestDto): BlogSearchResultListDo {
        val response = naverBlogSearchClient.search(
            NaverBlogSearchRequest(
                query = request.query,
                sort = request.sort.naver, // TODO: 좀더 Provier별로 분리할 필요가 있음
                start = request.page,
                display = request.size
            )
        ).awaitSingle()

        logger.info("naverResponse: {}", response)
        // TODO: reactor를 사용하여 webflux답게 변경하는 방법을 생각해볼것
        return BlogSearchResultListDo(
            result = response?.toBlogSearchResultDtoList() ?: listOf()
        )
    }
}
