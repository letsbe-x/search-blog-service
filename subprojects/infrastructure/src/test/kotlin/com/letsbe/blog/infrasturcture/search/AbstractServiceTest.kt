package com.letsbe.blog.infrasturcture.search

import com.letsbe.blog.infrastructure.rank.BlogRankClient
import com.letsbe.blog.infrastructure.search.external.kakao.KakaoBlogSearchClient
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = [
        TestApplication::class,
        KakaoBlogSearchClient::class,
        BlogRankClient::class
    ]
)
@RunWith(SpringRunner::class)
abstract class AbstractServiceTest
