package com.letsbe.search.blog.domain.dto

import com.letsbe.search.blog.domain.vo.SearchSortBySpec

data class SearchBlogRequestDto(
    val query: String,
    val sort: SearchSortBySpec,
    val page: Int,
    val size: Int
)
