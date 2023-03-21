package com.letsbe.blog.applications.rank.dto

typealias RankNo = Int

data class BlogRankDto(
    val rank: RankNo,
    val keyword: String,
    val count: Int
)
