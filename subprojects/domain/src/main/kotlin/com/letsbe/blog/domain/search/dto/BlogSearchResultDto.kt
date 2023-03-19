package com.letsbe.blog.domain.search.dto

import java.time.ZonedDateTime

data class BlogSearchResultDto(
    val title: String,
    val contents: String,
    val url: String,
    val datetime: ZonedDateTime
)
