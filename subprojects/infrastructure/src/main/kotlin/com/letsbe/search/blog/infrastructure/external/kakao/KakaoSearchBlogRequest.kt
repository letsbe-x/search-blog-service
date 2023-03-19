package com.letsbe.search.blog.infrastructure.external.kakao

data class KakaoSearchBlogRequest(
    val query: String,
    val sort: String = "accuracy",
    val page: Int = 1,
    val size: Int = 10
)
