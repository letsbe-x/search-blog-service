package com.letsbe.blog.infrasturcture.search.external

import com.letsbe.blog.infrastructure.search.external.kakao.KakaoBlogDocument
import com.letsbe.blog.infrastructure.search.external.kakao.KakaoBlogSearchClient
import com.letsbe.blog.infrastructure.search.external.kakao.KakaoBlogSearchRequest
import com.letsbe.blog.infrasturcture.search.AbstractServiceTest
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.test.web.client.match.MockRestRequestMatchers.anything
import org.springframework.test.web.client.match.MockRestRequestMatchers.method
import org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess
import org.springframework.web.client.RestTemplate
import java.time.ZonedDateTime

@ExtendWith(MockitoExtension::class)
class KakaoBlogSearchServiceTest : AbstractServiceTest() {

    @Autowired
    private lateinit var kakaoBlogSearchClient: KakaoBlogSearchClient

    @Test
    fun `KakaoBlogSearchResponse Mapper Test`() {
        // given
        val mockServer = MockRestServiceServer.createServer(RestTemplate())

        // will
        val request = KakaoBlogSearchRequest(
            query = "test",
            sort = "accuracy",
            page = 1,
            size = 10
        )

        val response = """
            {
                "meta": {
                    "is_end": false,
                    "pageable_count": 1,
                    "total_count": 1
                },
                "documents": [
                    {
                      "blogname": "이안의 부스러기들",
                      "contents": "유튜브 채널을 고르기가 쉽지만은 않다. 나만 그럴게 생각하는 건지는 몰라도…… 어쨌든 이번 <b>요리</b>는 우리나라 사람이면 대부분 알만한 <b>백종원</b>의 유튜브채널 “<b>백종원</b>의 <b>요리</b>비책” 이란 채널에 소개된 레시피이다. 가지밥. 다만 나는 여기에 고구마를 추가하고 다른 비빔양념장을 해서 먹었을 뿐. 재료손질도 오래 안...",
                      "datetime": "2023-02-23T09:00:34.000+09:00",
                      "thumbnail": "https://search2.kakaocdn.net/argon/130x130_85_c/39ZUHKDzDHQ",
                      "title": "#022 #가지밥 #<b>백종원</b>의<b>요리</b>비책 #따라잡기",
                      "url": "http://www.ians-crumbs.com/31"
                }
                ]
            }
        """.trimIndent()

        // TODO: 실제 통신됨
        mockServer.expect(anything())
            .andExpect(method(HttpMethod.GET))
            .andRespond(withSuccess(response, MediaType.APPLICATION_JSON))

        // then
        val results = runBlocking {
            kakaoBlogSearchClient.search(request).awaitSingle()
        }

        assertThat(results).isNotNull
        val (meta, documents) = results

        with(meta) {
            assertThat(is_end).isFalse
            assertThat(pageable_count).isEqualTo(1)
            assertThat(total_count).isEqualTo(1)
        }

        assertThat(documents.size == 1)
        val document: KakaoBlogDocument = documents.first()
        with(document) {
            assertThat(title).isEqualTo("이안의 부스러기들")
            assertThat(contents).isEqualTo("유튜브 채널을 고르기가 쉽지만은 않다. 나만 그럴게 생각하는 건지는 몰라도…… 어쨌든 이번 요리는 우리나라 사람이면 대부분 알만한 백종원의 유튜브채널 “백종원의 요리비책” 이란 채널에 소개된 레시피이다. 가지밥. 다만 나는 여기에 고구마를 추가하고 다른 비빔양념장을 해서 먹었을 뿐. 재료손질도 오래 안...")
            assertThat(datetime).isEqualTo(ZonedDateTime.parse("2023-02-23T09:00:34.000+09:00"))
            assertThat(url).isEqualTo("https://search2.kakaocdn.net/argon/130x130_85_c/39ZUHKDzDHQ")
        }
    }
}
