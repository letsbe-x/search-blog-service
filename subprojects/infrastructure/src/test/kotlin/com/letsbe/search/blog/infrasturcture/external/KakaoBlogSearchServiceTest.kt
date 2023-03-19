package com.letsbe.search.blog.infrasturcture.external

import com.letsbe.search.blog.infrasturcture.AbstractServiceTest

class KakaoBlogSearchServiceTest : AbstractServiceTest() {

//    @Autowired
//    private lateinit var kakaoSearchBlogClient: KakaoSearchBlogClient

//    @Test
//    @Disabled
//    fun `KakaoBlogSearchResponse Mapper Test`() {
//        val mockServer = MockRestServiceServer.createServer(RestTemplate())
//
//        val request = KakaoSearchBlogRequest(
//            query = "test",
//            sort = "accuracy",
//            page = 1,
//            size = 10
//        )
//
//        val response = """
//            {
//                "meta": {
//                    "is_end": false,
//                    "pageable_count": 1,
//                    "total_count": 1
//                },
//                "documents": [
//                    {
//                      "blogname": "이안의 부스러기들",
//                      "contents": "유튜브 채널을 고르기가 쉽지만은 않다. 나만 그럴게 생각하는 건지는 몰라도…… 어쨌든 이번 <b>요리</b>는 우리나라 사람이면 대부분 알만한 <b>백종원</b>의 유튜브채널 “<b>백종원</b>의 <b>요리</b>비책” 이란 채널에 소개된 레시피이다. 가지밥. 다만 나는 여기에 고구마를 추가하고 다른 비빔양념장을 해서 먹었을 뿐. 재료손질도 오래 안...",
//                      "datetime": "2023-02-23T09:00:34.000+09:00",
//                      "thumbnail": "https://search2.kakaocdn.net/argon/130x130_85_c/39ZUHKDzDHQ",
//                      "title": "#022 #가지밥 #<b>백종원</b>의<b>요리</b>비책 #따라잡기",
//                      "url": "http://www.ians-crumbs.com/31"
//                }
//                ]
//            }
//        """.trimIndent()
//
//        mockServer.expect(requestTo("https://dapi.kakao.com/v2/search/blog"))
//            .andExpect(method(HttpMethod.GET))
//            .andRespond(withSuccess(response, MediaType.APPLICATION_JSON))
//
// //        val results = kakaoSearchBlogClient.search(request).block()
// //
// //        assertThat(results).isNotNull
// //        with(results!!) {
// //            assertThat(meta["is_end"] == false)
// //            assertThat(documents.size == 1)
// //            assertThat(documents[0].title == "#022 #가지밥 #백종원의요리비책 #따라잡기")
// //        }
//    }
}
