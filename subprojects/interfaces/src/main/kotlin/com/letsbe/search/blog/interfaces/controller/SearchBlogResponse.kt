package com.letsbe.search.blog.interfaces.controller

import com.letsbe.search.blog.applications.dto.BlogPostDto

data class SearchBlogResponse(
    val blogPosts: List<BlogPostDto>
)
