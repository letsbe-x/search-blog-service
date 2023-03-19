package com.letsbe.blog.search.domain.aggregates

import com.letsbe.blog.search.domain.dto.SearchBlogResultDto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.ZonedDateTime

internal class SearchBlogResultListDoTest {
    @Test
    fun `Rule - orderbyNewest`() {
        val result = SearchBlogResultListDo(
            result = listOf(
                SearchBlogResultDto(
                    title = "title1",
                    contents = "contents1",
                    url = "url1",
                    datetime = ZonedDateTime.parse("2023-01-01T00:00:00.000+09:00")
                ),
                SearchBlogResultDto(
                    title = "title2",
                    contents = "contents2",
                    url = "url2",
                    datetime = ZonedDateTime.parse("2024-01-01T00:00:00.000+09:00")
                ),
                SearchBlogResultDto(
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
