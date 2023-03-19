package com.letsbe.search.blog.domain.vo

enum class SearchSortBySpec(val alias: String, val kakao: String) {
    ACCURACY("accuracy", "accuracy"),
    RECENCY("recency", "recency"),
    UNRECOGNIZED("unrecognized", "unrecognized")
}
