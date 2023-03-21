package com.letsbe.blog.applications.search.dto

import com.letsbe.blog.domain.search.dto.BlogSearchResultDto
import com.letsbe.blog.domain.search.vo.SearchProviderSpec
import java.time.ZonedDateTime

data class BlogPostDto(
    val title: String,
    val contents: String,
    val url: String,
    val datetime: ZonedDateTime,
    val searchProvider: SearchProviderSpec
) {
    // TODO: interface에서 사용하는 결과는 어떻게 정리할 것인가 생각
    companion object {
        fun from(BlogSearchResultDto: BlogSearchResultDto): BlogPostDto {
            return BlogPostDto(
                title = BlogSearchResultDto.title,
                contents = BlogSearchResultDto.contents,
                url = BlogSearchResultDto.url,
                datetime = BlogSearchResultDto.datetime,
                searchProvider = BlogSearchResultDto.searchProvider
            )
        }
    }
}
