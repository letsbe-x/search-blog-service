package com.letsbe.blog.domain.search.dto

import com.letsbe.blog.domain.search.vo.SearchProviderSpec
import java.time.ZonedDateTime

data class BlogSearchResultDto(
    val title: String,
    val contents: String,
    val url: String,
    val datetime: ZonedDateTime,
    val searchProvider: SearchProviderSpec
)
