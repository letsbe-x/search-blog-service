package com.letsbe.blog.domain.search.vo

enum class SearchSortBySpec(val alias: String, val kakao: String, val naver: String) {
    ACCURACY("accuracy", "accuracy", "date"),
    RECENCY("recency", "recency", "sim"),
    UNRECOGNIZED("unrecognized", "unrecognized", "unrecognized")
}
