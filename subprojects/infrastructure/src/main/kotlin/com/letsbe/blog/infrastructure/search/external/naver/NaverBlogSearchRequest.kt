package com.letsbe.blog.infrastructure.search.external.naver

data class NaverBlogSearchRequest(
    val query: String,
    val sort: String = "sim",
    val start: Int = 1,
    val display: Int = 10
)
