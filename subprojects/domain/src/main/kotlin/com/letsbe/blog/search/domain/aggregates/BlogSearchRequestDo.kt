package com.letsbe.blog.search.domain.aggregates

import com.letsbe.blog.search.domain.dto.BlogSearchRequestDto
import com.letsbe.blog.search.domain.vo.SearchSortBySpec
data class BlogSearchRequestDo(
    val query: String,
    val sort: String,
    val page: Int = 1,
    val size: Int = 10
) {
    fun toDto(): BlogSearchRequestDto {
        val sort = SearchSortBySpec.values().firstOrNull { it.alias == sort } ?: SearchSortBySpec.UNRECOGNIZED

        return BlogSearchRequestDto(
            query = query,
            sort = sort,
            page = page,
            size = size
        ).validate()
    }

    // TODO : 좀더 좋은 validate를 선언 할 수 있을 것 같은데
    private fun BlogSearchRequestDto.validate(): BlogSearchRequestDto {
        if (query.isBlank()) {
            throw IllegalArgumentException("query is blank")
        } else if (sort == SearchSortBySpec.UNRECOGNIZED) {
            throw IllegalArgumentException("sort is unrecognized")
        }
        return this
    }
}
