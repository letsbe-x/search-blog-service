package com.letsbe.blog.domain.search.vo

enum class SearchProviderSpec(val alias: String) {
    UNRECOGNIZED("unrecognized"),
    TOTAL("total"),
    KAKAO("kakao"),
    NAVER("naver")
}
