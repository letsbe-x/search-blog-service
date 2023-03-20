package com.letsbe.blog.infrastructure.search.external.naver

import com.letsbe.blog.domain.search.dto.BlogSearchResultDto
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

data class NaverBlogSearchResponse(
    val lastBuildDate: String,
    val total: Int,
    val start: Int,
    val display: Int,
    val items: List<NaverBlogItem>
) {
    fun toBlogSearchResultDtoList(): List<BlogSearchResultDto> {
        // TODO: Util Common
        val lastBuildDateFormatter = DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm:ss Z")
        val postDateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd")

        return this.items.map {
            BlogSearchResultDto(
                title = it.title,
                contents = it.description,
                url = it.link,
                datetime = ZonedDateTime.now() // TODO: parser
            )
        }.sortedByDescending { it.datetime }
    }
}

data class NaverBlogItem(
    val title: String,
    val link: String,
    val description: String,
    val bloggername: String,
    val bloggerlink: String,
    val postdate: String
)
