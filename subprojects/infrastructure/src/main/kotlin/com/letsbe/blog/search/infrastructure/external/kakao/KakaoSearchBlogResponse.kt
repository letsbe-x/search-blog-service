package com.letsbe.blog.search.infrastructure.external.kakao

import com.letsbe.blog.search.domain.dto.SearchBlogResultDto
import java.time.ZonedDateTime

data class KakaoSearchBlogResponse(
    val meta: KakaoSearchBlogMeta,
    val documents: List<KakaoBlogDocument> = listOf()
) {
    fun toSearchBlogResultDtoList(): List<SearchBlogResultDto> {
        return this.documents.map {
            SearchBlogResultDto(
                title = it.title,
                contents = it.contents,
                url = it.url,
                datetime = it.datetime
            )
        }.sortedByDescending { it.datetime }
    }
}

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
