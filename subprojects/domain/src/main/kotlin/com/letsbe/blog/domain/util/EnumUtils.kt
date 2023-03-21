package com.letsbe.blog.domain.util

object EnumUtils {
    inline fun <reified T : Enum<T>> T.nextOrNull(): T? {
        val values = enumValues<T>()
        val nextOrdinal = (ordinal + 1)
        return values.getOrNull(nextOrdinal)
    }
}
