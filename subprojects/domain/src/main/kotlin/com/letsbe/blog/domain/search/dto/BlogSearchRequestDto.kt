package com.letsbe.blog.domain.search.dto

import com.letsbe.blog.domain.search.vo.SearchProviderSpec
import com.letsbe.blog.domain.search.vo.SearchSortBySpec

data class BlogSearchRequestDto(
    val query: String,
    val sort: SearchSortBySpec = SearchSortBySpec.ACCURACY,
    val page: Int = 1,
    val size: Int = 10,
    val provider: SearchProviderSpec = SearchProviderSpec.KAKAO
)
