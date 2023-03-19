package com.letsbe.blog.search.domain.dto

import com.letsbe.blog.search.domain.vo.SearchSortBySpec

data class BlogSearchRequestDto(
    val query: String,
    val sort: SearchSortBySpec,
    val page: Int,
    val size: Int
)
