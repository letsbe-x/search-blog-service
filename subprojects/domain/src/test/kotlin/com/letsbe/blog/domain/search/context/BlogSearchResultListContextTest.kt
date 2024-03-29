package com.letsbe.blog.domain.search.context

import com.letsbe.blog.domain.search.dto.BlogSearchResultDto
import com.letsbe.blog.domain.search.vo.SearchProviderSpec
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.ZonedDateTime

internal class BlogSearchResultListContextTest {
    @Test
    fun `Rule - orderbyNewest`() {
        val result = BlogSearchResultListContext(
            result = listOf(
                BlogSearchResultDto(
                    title = "title1",
                    contents = "contents1",
                    url = "url1",
                    datetime = ZonedDateTime.parse("2023-01-01T00:00:00.000+09:00"),
                    searchProvider = SearchProviderSpec.KAKAO
                ),
                BlogSearchResultDto(
                    title = "title2",
                    contents = "contents2",
                    url = "url2",
                    datetime = ZonedDateTime.parse("2024-01-01T00:00:00.000+09:00"),
                    searchProvider = SearchProviderSpec.KAKAO
                ),
                BlogSearchResultDto(
                    title = "title3",
                    contents = "contents3",
                    url = "url3",
                    datetime = ZonedDateTime.parse("2025-01-01T00:00:00.000+09:00"),
                    searchProvider = SearchProviderSpec.KAKAO
                )
            )
        ).toDtoList()

        assertThat(result).hasSize(3)
        assertThat(result.zipWithNext().all { it.first.datetime >= it.second.datetime }).isTrue
    }
}
