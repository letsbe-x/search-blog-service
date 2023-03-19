package com.letsbe.search.blog.infrastructure.external.kakao

import java.time.ZonedDateTime

data class KakaoSearchBlogResponse(
    val meta: KakaoSearchBlogMeta,
    val documents: List<KakaoBlogDocument> = listOf()
)

data class KakaoSearchBlogMeta(
    val is_end: Boolean,
    val pageable_count: Int,
    val total_count: Int
)

data class KakaoBlogDocument(
    val title: String,
    val contents: String,
    val url: String,
    val datetime: ZonedDateTime
)
