package com.letsbe.blog.domain.search.aggregates

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

internal class BlogSearchRequestDoTest {

    @Test
    fun validate() {
        assertDoesNotThrow {
            BlogSearchRequestDo("query", "accuracy")
        }

        assertThrows<IllegalArgumentException> {
            BlogSearchRequestDo("query", "unrecognized")
        }

        assertThrows<IllegalArgumentException> {
            BlogSearchRequestDo("", "recency")
        }
    }
}
