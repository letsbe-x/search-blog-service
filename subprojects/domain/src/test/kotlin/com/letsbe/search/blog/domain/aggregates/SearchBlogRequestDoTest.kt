package com.letsbe.search.blog.domain.aggregates

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

internal class SearchBlogRequestDoTest {

    @Test
    fun validate() {
        assertDoesNotThrow {
            SearchBlogRequestDo.from("query", "accuracy")
        }

        assertThrows<IllegalArgumentException> {
            SearchBlogRequestDo.from("query", "unrecognized")
        }

        assertThrows<IllegalArgumentException> {
            SearchBlogRequestDo.from("", "recency")
        }
    }
}
