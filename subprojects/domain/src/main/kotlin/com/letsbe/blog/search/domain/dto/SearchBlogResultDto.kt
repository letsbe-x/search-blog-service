package com.letsbe.blog.search.domain.dto

import java.time.ZonedDateTime

data class SearchBlogResultDto(
    val title: String,
    val contents: String,
    val url: String,
    val datetime: ZonedDateTime
)
