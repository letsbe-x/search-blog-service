package com.letsbe.blog.domain.search.aggregates

import com.letsbe.blog.domain.search.dto.BlogSearchResultDto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.ZonedDateTime

internal class BlogSearchResultListDoTest {
    @Test
    fun `Rule - orderbyNewest`() {
        val result = BlogSearchResultListDo(
            result = listOf(
                BlogSearchResultDto(
                    title = "title1",
                    contents = "contents1",
                    url = "url1",
                    datetime = ZonedDateTime.parse("2023-01-01T00:00:00.000+09:00")
                ),
                BlogSearchResultDto(
                    title = "title2",
                    contents = "contents2",
                    url = "url2",
                    datetime = ZonedDateTime.parse("2024-01-01T00:00:00.000+09:00")
                ),
                BlogSearchResultDto(
                    title = "title3",
                    contents = "contents3",
                    url = "url3",
                    datetime = ZonedDateTime.parse("2025-01-01T00:00:00.000+09:00")
                )
            )
        ).toDtoList()

        assertThat(result).hasSize(3)
        assertThat(result.zipWithNext().all { it.first.datetime >= it.second.datetime }).isTrue
    }
}
