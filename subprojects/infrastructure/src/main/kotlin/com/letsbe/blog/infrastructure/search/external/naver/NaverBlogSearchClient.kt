package com.letsbe.blog.infrastructure.search.external.naver

import com.letsbe.blog.domain.search.dto.BlogSearchRequestDto
import com.letsbe.blog.domain.search.dto.BlogSearchResultDto
import com.letsbe.blog.infrastructure.configuration.NaverBlogSearchProperties
import com.letsbe.blog.infrastructure.search.external.iface.BlogSearchClient
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.ZonedDateTime

@Component
@ConfigurationPropertiesScan(
    basePackages = [
        "com.letsbe.blog.search.infrastructure.configuration"
    ]
)
class NaverBlogSearchClient(
    naverBlogSearchProperties: NaverBlogSearchProperties
) : BlogSearchClient {

    /**
     * Naver Blog Search API V2
     * - ref: https://developers.naver.com/docs/serviceapi/search/blog/blog.md
     */
    private val baseUrl = naverBlogSearchProperties.baseUrl
    private val clientId = naverBlogSearchProperties.clientId
    private val clientSecret = naverBlogSearchProperties.clientSecret

    private val webClient = WebClient.builder()
        .baseUrl(baseUrl)
        .defaultHeader("X-Naver-Client-Id", clientId)
        .defaultHeader("X-Naver-Client-Secret", clientSecret)
        .build()

    override suspend fun search(request: BlogSearchRequestDto): Flux<BlogSearchResultDto> {
        val naverBlogSearchRequest = buildBlogSearchRequest(request)

        val response = performSearchRequest(naverBlogSearchRequest)

        return processSearchResponse(response)
    }

    private fun buildBlogSearchRequest(request: BlogSearchRequestDto) =
        NaverBlogSearchRequest(
            query = request.query,
            sort = request.sort.naver,
            start = request.page,
            display = request.size
        )

    private fun performSearchRequest(naverBlogSearchRequest: NaverBlogSearchRequest): Mono<NaverBlogSearchResponse> {
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

    private fun processSearchResponse(response: Mono<NaverBlogSearchResponse>): Flux<BlogSearchResultDto> {
        return response.map { it.items }
            .flatMapMany { Flux.fromIterable(it) }
            .map {
                BlogSearchResultDto(
                    title = it.title,
                    contents = it.description,
                    url = it.link,
                    datetime = ZonedDateTime.now() // TODO: parser
                )
            }
    }
}
