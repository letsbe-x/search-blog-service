package com.letsbe.blog.infrasturcture.search

import com.letsbe.blog.infrastructure.search.external.kakao.KakaoBlogSearchClient
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = [
        TestApplication::class,
        KakaoBlogSearchClient::class
    ]
)
abstract class AbstractServiceTest
