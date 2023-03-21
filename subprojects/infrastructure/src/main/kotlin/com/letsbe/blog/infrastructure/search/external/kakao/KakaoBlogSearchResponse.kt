package com.letsbe.blog.infrastructure.search.external.kakao

import java.time.ZonedDateTime

data class KakaoBlogSearchResponse(
    val meta: KakaoBlogSearchMeta,
    val documents: List<KakaoBlogDocument>
)

data class KakaoBlogSearchMeta(
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
