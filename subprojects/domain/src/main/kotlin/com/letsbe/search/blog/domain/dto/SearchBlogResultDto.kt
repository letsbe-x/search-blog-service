package com.letsbe.search.blog.domain.dto

import java.time.ZonedDateTime

data class SearchBlogResultDto(
    val title: String,
    val contents: String,
    val url: String,
    val datetime: ZonedDateTime
)
