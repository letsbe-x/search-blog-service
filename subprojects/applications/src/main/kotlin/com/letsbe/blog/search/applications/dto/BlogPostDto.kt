package com.letsbe.blog.search.applications.dto

import com.letsbe.blog.search.domain.dto.BlogSearchResultDto
import java.time.ZonedDateTime

data class BlogPostDto(
    val title: String,
    val contents: String,
    val url: String,
    val datetime: ZonedDateTime
) {
    // TODO: interface에서 사용하는 결과는 어떻게 정리할 것인가 생각
    companion object {
        fun from(BlogSearchResultDto: BlogSearchResultDto): BlogPostDto {
            return BlogPostDto(
                title = BlogSearchResultDto.title,
                contents = BlogSearchResultDto.contents,
                url = BlogSearchResultDto.url,
                datetime = BlogSearchResultDto.datetime
            )
        }
    }
}
