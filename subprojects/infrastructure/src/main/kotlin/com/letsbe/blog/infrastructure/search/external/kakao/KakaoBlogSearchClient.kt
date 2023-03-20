package com.letsbe.blog.infrastructure.search.external.kakao

import com.letsbe.blog.domain.search.dto.BlogSearchRequestDto
import com.letsbe.blog.domain.search.dto.BlogSearchResultDto
import com.letsbe.blog.infrastructure.configuration.KakaoBlogSearchProperties
import com.letsbe.blog.infrastructure.search.external.iface.BlogSearchClient
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
@ConfigurationPropertiesScan(
    basePackages = [
        "com.letsbe.blog.search.infrastructure.configuration"
    ]
)
class KakaoBlogSearchClient(
    kakaoBlogSearchProperties: KakaoBlogSearchProperties
) : BlogSearchClient {

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

    override suspend fun search(request: BlogSearchRequestDto): Flux<BlogSearchResultDto> {
        val kakaoBlogSearchRequest = buildBlogSearchRequest(request)

        val response = performSearchRequest(kakaoBlogSearchRequest)

        return processSearchRequest(response)
    }

    private fun buildBlogSearchRequest(request: BlogSearchRequestDto) =
        KakaoBlogSearchRequest(
            query = request.query,
            sort = request.sort.kakao,
            page = request.page,
            size = request.size
        )

    private fun performSearchRequest(kakaoBlogSearchRequest: KakaoBlogSearchRequest): Mono<KakaoBlogSearchResponse> {
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

    private fun processSearchRequest(response: Mono<KakaoBlogSearchResponse>) =
        response
            .map { it.documents }
            .flatMapMany { Flux.fromIterable(it) }
            .map {
                BlogSearchResultDto(
                    title = it.title,
                    contents = it.contents,
                    url = it.url,
                    datetime = it.datetime
                )
            }
}
