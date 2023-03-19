package com.letsbe.blog.search.infrastructure.external.kakao

data class KakaoBlogSearchRequest(
    val query: String,
    val sort: String = "accuracy",
    val page: Int = 1,
    val size: Int = 10
)
