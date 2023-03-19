package com.letsbe.blog.search.infrastructure.external.kakao

import com.letsbe.blog.search.infrastructure.configuration.KakaoSearchBlogProperties
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
class KakaoSearchBlogClient(
    kakaoSearchBlogProperties: KakaoSearchBlogProperties
) {

    /**
     * Kakao Blog Search API V2
     * - ref: https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide
     */
    private val baseUrl = kakaoSearchBlogProperties.baseUrl
    private val token = kakaoSearchBlogProperties.token

    val webClient = WebClient.builder()
        .baseUrl(baseUrl)
        .defaultHeader("Authorization", "KakaoAK $token")
        .build()

    suspend fun search(kakaoSearchBlogRequest: KakaoSearchBlogRequest): Mono<KakaoSearchBlogResponse> {
        return webClient.get()
            .uri {
                it.queryParam("query", kakaoSearchBlogRequest.query)
                    .queryParam("sort", kakaoSearchBlogRequest.sort)
                    .queryParam("page", kakaoSearchBlogRequest.page)
                    .queryParam("size", kakaoSearchBlogRequest.size)
                    .build()
            }
            .retrieve()
            .bodyToMono()
    }
}
