package com.letsbe.search.blog.applications.dto

import com.letsbe.search.blog.domain.dto.SearchBlogResultDto
import java.time.ZonedDateTime

data class BlogPostDto(
    val title: String,
    val contents: String,
    val url: String,
    val datetime: ZonedDateTime
) {
    // TODO: interface에서 사용하는 결과는 어떻게 정리할 것인가 생각
    companion object {
        fun from(SearchBlogResultDto: SearchBlogResultDto): BlogPostDto {
            return BlogPostDto(
                title = SearchBlogResultDto.title,
                contents = SearchBlogResultDto.contents,
                url = SearchBlogResultDto.url,
                datetime = SearchBlogResultDto.datetime
            )
        }
    }
}
