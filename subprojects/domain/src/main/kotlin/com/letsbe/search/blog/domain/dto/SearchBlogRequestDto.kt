package com.letsbe.search.blog.domain.dto

import com.letsbe.search.blog.domain.vo.SearchSortBySpec

data class SearchBlogRequestDto(
    val query: String,
    val sort: SearchSortBySpec = SearchSortBySpec.ACCURACY
) {
    companion object {
        fun from(query: String, sort: String): SearchBlogRequestDto {
            return SearchBlogRequestDto(
                query = query,
                sort = SearchSortBySpec.values().firstOrNull { it.alias == sort } ?: SearchSortBySpec.UNRECOGNIZED
            ).validate()
        }
    }

    // TODO : 좀더 좋은 validate를 선언 할 수 있을 것 같은데
    fun validate(): SearchBlogRequestDto {
        if (query.isBlank()) {
            throw IllegalArgumentException("query is blank")
        } else if (sort == SearchSortBySpec.UNRECOGNIZED) {
            throw IllegalArgumentException("sort is unrecognized")
        }
        return this
    }
}
