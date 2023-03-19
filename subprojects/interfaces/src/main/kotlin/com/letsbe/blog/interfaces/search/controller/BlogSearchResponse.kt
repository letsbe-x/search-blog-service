package com.letsbe.blog.interfaces.search.controller

import com.letsbe.blog.applications.search.dto.BlogPostDto

data class BlogSearchResponse(
    val blogPosts: List<BlogPostDto>
)
