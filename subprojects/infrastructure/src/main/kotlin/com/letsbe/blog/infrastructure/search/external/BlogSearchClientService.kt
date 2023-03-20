package com.letsbe.blog.infrastructure.search.external

import com.letsbe.blog.domain.search.dto.BlogSearchRequestDto
import com.letsbe.blog.domain.search.dto.BlogSearchResultDto
import com.letsbe.blog.domain.search.vo.SearchProviderSpec
import com.letsbe.blog.infrastructure.search.external.kakao.KakaoBlogSearchClient
import com.letsbe.blog.infrastructure.search.external.naver.NaverBlogSearchClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class BlogSearchClientService(
    private val kakaoBlogSearchClient: KakaoBlogSearchClient,
    private val naverBlogSearchClient: NaverBlogSearchClient
) {
    val logger: Logger = LoggerFactory.getLogger(this::class.java)
    suspend fun search(request: BlogSearchRequestDto): Flux<BlogSearchResultDto> {
        return when (request.provider) {
            SearchProviderSpec.KAKAO -> kakaoBlogSearchClient.search(request)
            SearchProviderSpec.NAVER -> naverBlogSearchClient.search(request)
        }
    }
}
