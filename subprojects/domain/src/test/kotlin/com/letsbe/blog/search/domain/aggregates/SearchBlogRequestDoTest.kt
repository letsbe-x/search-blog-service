package com.letsbe.blog.search.domain.aggregates

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

internal class SearchBlogRequestDoTest {

    @Test
    fun validate() {
        assertDoesNotThrow {
            SearchBlogRequestDo("query", "accuracy")
        }

        assertThrows<IllegalArgumentException> {
            SearchBlogRequestDo("query", "unrecognized")
        }

        assertThrows<IllegalArgumentException> {
            SearchBlogRequestDo("", "recency")
        }
    }
}
