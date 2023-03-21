package com.letsbe.blog.infrasturcture.search.external

import com.letsbe.blog.domain.search.dto.BlogSearchRequestDto
import com.letsbe.blog.domain.search.dto.BlogSearchResultDto
import com.letsbe.blog.domain.search.vo.SearchProviderSpec
import com.letsbe.blog.domain.search.vo.SearchSortBySpec
import com.letsbe.blog.infrastructure.search.external.BlogSearchClientService
import com.letsbe.blog.infrastructure.search.external.kakao.KakaoBlogSearchClient
import com.letsbe.blog.infrastructure.search.external.naver.NaverBlogSearchClient
import com.letsbe.blog.infrasturcture.search.AbstractServiceTest
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.never
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.web.reactive.function.client.WebClientResponseException
import reactor.core.publisher.Flux
import reactor.test.StepVerifier
import java.time.ZonedDateTime

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [BlogSearchClientService::class])
class BlogSearchClientServiceTest : AbstractServiceTest() {

    @Autowired
    lateinit var blogSearchClientService: BlogSearchClientService

    @MockBean
    lateinit var kakaoBlogSearchClient: KakaoBlogSearchClient

    @MockBean
    lateinit var naverBlogSearchClient: NaverBlogSearchClient

    @Test
    fun `kakaoSearch Test`() {
        val request = BlogSearchRequestDto("query", SearchSortBySpec.ACCURACY, 1, 10)
        val expectedResult = Flux.just(BlogSearchResultDto("title", "contend", "url", ZonedDateTime.now(), SearchProviderSpec.KAKAO))

        runBlocking {
            `when`(kakaoBlogSearchClient.search(request)).thenReturn(expectedResult)

            val actualResult = blogSearchClientService.search(request)

            StepVerifier.create(actualResult)
                .assertNext { result ->
                    assertThat(result.searchProvider).isEqualTo(SearchProviderSpec.KAKAO)
                }
                .expectComplete()
                .verify()

            verify(kakaoBlogSearchClient, times(1)).search(request)
            verify(naverBlogSearchClient, never()).search(request)
        }
    }

    @Test
    fun `naverSearch Test when kakaoApi failover`() {
        val request = BlogSearchRequestDto("query", SearchSortBySpec.ACCURACY, 1, 10)
        val expectedResult = Flux.just(BlogSearchResultDto("title", "contend", "url", ZonedDateTime.now(), SearchProviderSpec.KAKAO))

        runBlocking {
            `when`(kakaoBlogSearchClient.search(request)).thenReturn(Flux.error(WebClientResponseException("Kakao API is not available", 400, "Kakao API is not available", null, null, null)))
            `when`(naverBlogSearchClient.search(request)).thenReturn(expectedResult)

            val actualResult = blogSearchClientService.search(request)

            StepVerifier.create(actualResult)
                .expectNextMatches { result ->
                    result.searchProvider == SearchProviderSpec.NAVER
                }
                .expectComplete()
                .verify()

            verify(kakaoBlogSearchClient, times(1)).search(request)
            verify(naverBlogSearchClient, times(1)).search(request)
        }
    }
}
