package com.letsbe.blog.infrastructure.search.external.naver

data class NaverBlogSearchResponse(
    val lastBuildDate: String,
    val total: Int,
    val start: Int,
    val display: Int,
    val items: List<NaverBlogItem>
)

data class NaverBlogItem(
    val title: String,
    val link: String,
    val description: String,
    val bloggername: String,
    val bloggerlink: String,
    val postdate: String
)
