package com.letsbe.blog.domain.search.context

import com.letsbe.blog.domain.search.dto.BlogSearchRequestDto
import com.letsbe.blog.domain.search.vo.SearchProviderSpec
import com.letsbe.blog.domain.search.vo.SearchSortBySpec
data class BlogSearchRequestContext(
    val query: String,
    val sort: String,
    val page: Int = 1,
    val size: Int = 10,
    val provider: String = "kakao"
) {
    fun toDto(): BlogSearchRequestDto {
        val sort = SearchSortBySpec.values().firstOrNull { it.alias == sort } ?: SearchSortBySpec.UNRECOGNIZED
        val provider = SearchProviderSpec.values().firstOrNull { it.alias == provider } ?: SearchProviderSpec.UNRECOGNIZED

        return BlogSearchRequestDto(
            query = query,
            sort = sort,
            page = page,
            size = size,
            provider = provider
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
