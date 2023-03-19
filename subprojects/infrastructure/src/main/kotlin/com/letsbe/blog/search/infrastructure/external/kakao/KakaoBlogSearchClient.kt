package com.letsbe.blog.search.infrastructure.external.kakao

import com.letsbe.blog.search.infrastructure.configuration.KakaoBlogSearchProperties
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
class KakaoBlogSearchClient(
    kakaoBlogSearchProperties: KakaoBlogSearchProperties
) {

    /**
     * Kakao Blog Search API V2
     * - ref: https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide
     */
    private val baseUrl = kakaoBlogSearchProperties.baseUrl
    private val token = kakaoBlogSearchProperties.token

    val webClient = WebClient.builder()
        .baseUrl(baseUrl)
        .defaultHeader("Authorization", "KakaoAK $token")
        .build()

    suspend fun search(kakaoBlogSearchRequest: KakaoBlogSearchRequest): Mono<KakaoBlogSearchResponse> {
        return webClient.get()
            .uri {
                it.queryParam("query", kakaoBlogSearchRequest.query)
                    .queryParam("sort", kakaoBlogSearchRequest.sort)
                    .queryParam("page", kakaoBlogSearchRequest.page)
                    .queryParam("size", kakaoBlogSearchRequest.size)
                    .build()
            }
            .retrieve()
            .bodyToMono()
    }
}
