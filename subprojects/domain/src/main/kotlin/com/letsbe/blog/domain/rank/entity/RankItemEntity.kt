package com.letsbe.blog.domain.rank.entity

typealias RankItemEntityId = String

data class RankItemEntity(
    val keyword: RankItemEntityId,
    val requestCount: Int
)
