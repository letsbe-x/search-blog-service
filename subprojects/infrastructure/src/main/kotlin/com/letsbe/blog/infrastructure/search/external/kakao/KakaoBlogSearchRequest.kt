package com.letsbe.blog.infrastructure.search.external.kakao

data class KakaoBlogSearchRequest(
    val query: String,
    val sort: String = "accuracy",
    val page: Int = 1,
    val size: Int = 10
)
