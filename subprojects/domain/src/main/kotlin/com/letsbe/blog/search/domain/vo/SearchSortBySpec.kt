package com.letsbe.blog.search.domain.vo

enum class SearchSortBySpec(val alias: String, val kakao: String) {
    ACCURACY("accuracy", "accuracy"),
    RECENCY("recency", "recency"),
    UNRECOGNIZED("unrecognized", "unrecognized")
}
