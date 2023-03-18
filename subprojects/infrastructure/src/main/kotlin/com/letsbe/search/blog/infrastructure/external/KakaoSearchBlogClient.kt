package com.letsbe.search.blog.infrastructure.external

import com.letsbe.search.blog.infrastructure.configuration.KakaoSearchBlogProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono

@Component
@ConfigurationPropertiesScan(
    basePackages = [
        "com.letsbe.search.blog.infrastructure.configuration"
    ]
)
class KakaoSearchBlogClient(
    webClientBuilder: WebClient.Builder,
    kakaoSearchBlogProperties: KakaoSearchBlogProperties
) {
    /**
     * Kakao Blog Search API V2
     * - ref: https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide
     */
    private val baseUrl = kakaoSearchBlogProperties.baseUrl
    private val token = kakaoSearchBlogProperties.token
    private val webClient = webClientBuilder.baseUrl(baseUrl).build()

    fun search(kakaoSearchBlogRequest: KakaoSearchBlogRequest): Mono<KakaoSearchBlogResponse> {
        return webClient.get()
            .uri {
                it.queryParam("query", kakaoSearchBlogRequest.query)
                    .queryParam("sort", kakaoSearchBlogRequest.sort)
                    .queryParam("page", kakaoSearchBlogRequest.page)
                    .queryParam("size", kakaoSearchBlogRequest.size)
                    .build()
            }
            .header("Authorization", "KakaoAK $token")
            .retrieve()
            .bodyToMono()
    }
}

data class KakaoSearchBlogRequest(
    val query: String,
    val sort: String = "accuracy",
    val page: Int = 1,
    val size: Int = 10
)

data class KakaoSearchBlogResponse(
    val meta: Map<String, Any>,
    val documents: List<KakaoBlogDocument>
)

data class KakaoBlogDocument(
    val title: String,
    val contents: String,
    val url: String,
    val datetime: String
) {
    fun toBlogPostDTO(): BlogPostDTO {
        return BlogPostDTO(
            title = this.title,
            contents = this.contents,
            url = this.url,
            datetime = this.datetime
        )
    }
}

data class BlogPostDTO(
    val title: String,
    val contents: String,
    val url: String,
    val datetime: String
)
