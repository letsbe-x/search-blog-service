package com.letsbe.blog.search.infrastructure.external.kakao

import com.letsbe.blog.search.domain.dto.BlogSearchResultDto
import java.time.ZonedDateTime

data class KakaoBlogSearchResponse(
    val meta: KakaoBlogSearchMeta,
    val documents: List<KakaoBlogDocument> = listOf()
) {
    fun toBlogSearchResultDtoList(): List<BlogSearchResultDto> {
        return this.documents.map {
            BlogSearchResultDto(
                title = it.title,
                contents = it.contents,
                url = it.url,
                datetime = it.datetime
            )
        }.sortedByDescending { it.datetime }
    }
}

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
