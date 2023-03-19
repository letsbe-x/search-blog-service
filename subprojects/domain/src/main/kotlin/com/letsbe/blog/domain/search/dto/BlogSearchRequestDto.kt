package com.letsbe.blog.domain.search.dto

import com.letsbe.blog.domain.search.vo.SearchSortBySpec

data class BlogSearchRequestDto(
    val query: String,
    val sort: SearchSortBySpec,
    val page: Int,
    val size: Int
)
