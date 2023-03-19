package com.letsbe.search.blog.infrastructure.external.dto

import com.letsbe.search.blog.infrastructure.external.kakao.KakaoBlogDocument
import java.time.ZonedDateTime

data class BlogPostDTO(
    val title: String,
    val contents: String,
    val url: String,
    val datetime: ZonedDateTime
) {

    companion object {
        fun from(kakaoBlogDocument: KakaoBlogDocument): BlogPostDTO {
            return BlogPostDTO(
                title = kakaoBlogDocument.title,
                contents = kakaoBlogDocument.contents,
                url = kakaoBlogDocument.url,
                datetime = kakaoBlogDocument.datetime
            )
        }
    }
}
