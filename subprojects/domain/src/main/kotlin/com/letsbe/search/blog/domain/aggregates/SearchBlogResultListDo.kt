package com.letsbe.search.blog.domain.aggregates

import com.letsbe.search.blog.domain.dto.SearchBlogResultDto

class SearchBlogResultListDo(
    private val result: List<SearchBlogResultDto>
) {
    fun toDtoList(): List<SearchBlogResultDto> {
        return this.orderbyNewest().result
    }

    private fun orderbyNewest(): SearchBlogResultListDo {
        // TODO: 시간이 같을때는 UUID를 추가이용하여 정렬을 하는게 좋아보임 / Item별 구분 지을 수 있는게 없다.
        // TODO: 구분자로 블로그의 url을 변경하여 사용하면, 유일한 값이 됨
        return SearchBlogResultListDo(
            result = this.result.sortedByDescending { it.datetime }
        )
    }
}
