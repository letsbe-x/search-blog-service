package com.letsbe.blog.search.interfaces.controller

import com.letsbe.blog.search.applications.dto.BlogPostDto

data class BlogSearchResponse(
    val blogPosts: List<BlogPostDto>
)
