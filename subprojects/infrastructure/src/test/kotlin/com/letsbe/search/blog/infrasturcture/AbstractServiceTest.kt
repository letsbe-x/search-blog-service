package com.letsbe.search.blog.infrasturcture

import com.letsbe.search.blog.infrastructure.external.kakao.KakaoSearchBlogClient
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = [
        TestApplication::class,
        KakaoSearchBlogClient::class
    ]
)
abstract class AbstractServiceTest
