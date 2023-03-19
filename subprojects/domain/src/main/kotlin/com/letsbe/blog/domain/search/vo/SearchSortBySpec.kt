package com.letsbe.blog.domain.search.vo

enum class SearchSortBySpec(val alias: String, val kakao: String) {
    ACCURACY("accuracy", "accuracy"),
    RECENCY("recency", "recency"),
    UNRECOGNIZED("unrecognized", "unrecognized")
}
