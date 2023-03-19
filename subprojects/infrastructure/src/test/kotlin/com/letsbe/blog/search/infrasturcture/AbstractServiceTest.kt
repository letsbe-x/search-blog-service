package com.letsbe.blog.search.infrasturcture

import com.letsbe.blog.search.infrastructure.external.kakao.KakaoBlogSearchClient
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = [
        TestApplication::class,
        KakaoBlogSearchClient::class
    ]
)
abstract class AbstractServiceTest
