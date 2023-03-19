package com.letsbe.blog.interfaces.rank.controller

import com.letsbe.blog.applications.rank.dto.BlogRankDto

data class BlogRankResponse(
    val ranks: List<BlogRankDto>
)
