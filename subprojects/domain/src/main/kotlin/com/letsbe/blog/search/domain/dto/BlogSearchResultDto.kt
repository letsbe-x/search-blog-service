package com.letsbe.blog.search.domain.dto

import java.time.ZonedDateTime

data class BlogSearchResultDto(
    val title: String,
    val contents: String,
    val url: String,
    val datetime: ZonedDateTime
)
