package com.letsbe.search.blog.applications.blog

import com.letsbe.search.blog.infrastructure.external.BlogPostDTO

data class BlogPostDO(
    val title: String,
    val contents: String,
    val url: String,
    val datetime: String
) {
    companion object {
        fun from(blogPostDTO: BlogPostDTO): BlogPostDO {
            return BlogPostDO(
                title = blogPostDTO.title,
                contents = blogPostDTO.contents,
                url = blogPostDTO.url,
                datetime = blogPostDTO.datetime
            )
        }
    }
}
