package com.letsbe.blog.domain.search.context

import com.letsbe.blog.domain.search.dto.BlogSearchResultDto

class BlogSearchResultListContext(
    private val result: List<BlogSearchResultDto>
) {
    fun toDtoList(): List<BlogSearchResultDto> {
        return this.orderbyNewest().result
    }

    private fun orderbyNewest(): BlogSearchResultListContext {
        // TODO: 시간이 같을때는 UUID를 추가이용하여 정렬을 하는게 좋아보임 / Item별 구분 지을 수 있는게 없다.
        // TODO: 구분자로 블로그의 url을 변경하여 사용하면, 유일한 값이 됨
        return BlogSearchResultListContext(
            result = this.result.sortedByDescending { it.datetime }
        )
    }
}
