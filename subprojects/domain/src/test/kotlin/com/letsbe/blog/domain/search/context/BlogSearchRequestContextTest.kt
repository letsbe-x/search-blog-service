package com.letsbe.blog.domain.search.context

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

internal class BlogSearchRequestContextTest {

    @Test
    fun validate() {
        assertDoesNotThrow {
            BlogSearchRequestContext("query", "accuracy")
        }

        assertThrows<IllegalArgumentException> {
            BlogSearchRequestContext("query", "unrecognized")
        }

        assertThrows<IllegalArgumentException> {
            BlogSearchRequestContext("", "recency")
        }
    }
}
