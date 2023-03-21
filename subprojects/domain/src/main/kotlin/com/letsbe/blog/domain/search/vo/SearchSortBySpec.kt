package com.letsbe.blog.domain.search.vo

enum class SearchSortBySpec(val alias: String, val kakao: String, val naver: String) {
    UNRECOGNIZED("unrecognized", "unrecognized", "unrecognized"),
    ACCURACY("accuracy", "accuracy", "date"),
    RECENCY("recency", "recency", "sim")
}
