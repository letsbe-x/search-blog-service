package com.letsbe.blog.applications.rank.dto

typealias RankNo = Int
typealias Keyword = String
data class BlogRankDto(
    val rank: RankNo,
    val keyword: String,
    val count: Int
)
