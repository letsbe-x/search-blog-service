package com.letsbe.blog.infrastructure.search.external

import com.letsbe.blog.domain.search.dto.BlogSearchRequestDto
import com.letsbe.blog.domain.search.dto.BlogSearchResultDto
import com.letsbe.blog.domain.search.vo.SearchProviderSpec
import com.letsbe.blog.domain.util.EnumUtils.nextOrNull
import com.letsbe.blog.infrastructure.search.external.kakao.KakaoBlogSearchClient
import com.letsbe.blog.infrastructure.search.external.naver.NaverBlogSearchClient
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClientResponseException
import reactor.core.publisher.Flux

@Service
class BlogSearchClientService(
    private val kakaoBlogSearchClient: KakaoBlogSearchClient,
    private val naverBlogSearchClient: NaverBlogSearchClient
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    suspend fun search(request: BlogSearchRequestDto): Flux<BlogSearchResultDto> {
        return try {
            when (request.provider) {
                SearchProviderSpec.TOTAL ->
                    Flux.merge(kakaoBlogSearchClient.search(request), naverBlogSearchClient.search(request)).take(request.size.toLong()) // 검색결과가 적은 경우, 같이 노출됩니다.
                SearchProviderSpec.KAKAO ->
                    kakaoBlogSearchClient.search(request)
                SearchProviderSpec.NAVER ->
                    naverBlogSearchClient.search(request)
                else -> throw IllegalArgumentException("Invalid provider: ${request.provider}")
            }
        } catch (e: WebClientResponseException) {
            logger.error("BlogSearchClientService.search: {}", e.message)
            logger.error("{} API is not available", request.provider)
            search(
                request.copy(
                    provider = request.provider.nextOrNull()
                        ?: return Flux.empty()
                )
            )
        }
    }
}
