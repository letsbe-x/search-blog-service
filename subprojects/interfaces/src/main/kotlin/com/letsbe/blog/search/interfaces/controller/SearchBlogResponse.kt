package com.letsbe.blog.search.interfaces.controller

import com.letsbe.blog.search.applications.dto.BlogPostDto

data class SearchBlogResponse(
    val blogPosts: List<BlogPostDto>
)
