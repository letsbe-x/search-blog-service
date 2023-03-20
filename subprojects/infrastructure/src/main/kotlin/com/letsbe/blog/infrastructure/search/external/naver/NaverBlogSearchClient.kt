package com.letsbe.blog.infrastructure.search.external.naver

import com.letsbe.blog.infrastructure.configuration.NaverBlogSearchProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono

@Component
@ConfigurationPropertiesScan(
    basePackages = [
        "com.letsbe.blog.search.infrastructure.configuration"
    ]
)
class NaverBlogSearchClient(
    naverBlogSearchProperties: NaverBlogSearchProperties
) {

    /**
     * Naver Blog Search API V2
     * - ref: https://developers.naver.com/docs/serviceapi/search/blog/blog.md
     */
    private val baseUrl = naverBlogSearchProperties.baseUrl
    private val clientId = naverBlogSearchProperties.clientId
    private val clientSecret = naverBlogSearchProperties.clientSecret

    val webClient = WebClient.builder()
        .baseUrl(baseUrl)
        .defaultHeader("X-Naver-Client-Id", clientId)
        .defaultHeader("X-Naver-Client-Secret", clientSecret)
        .build()

    suspend fun search(naverBlogSearchRequest: NaverBlogSearchRequest): Mono<NaverBlogSearchResponse> {
        return webClient.get()
            .uri {
                it.queryParam("query", naverBlogSearchRequest.query)
                    .queryParam("sort", naverBlogSearchRequest.sort)
                    .queryParam("start", naverBlogSearchRequest.start)
                    .queryParam("display", naverBlogSearchRequest.display)
                    .build()
            }
            .retrieve()
            .bodyToMono()
    }
}
