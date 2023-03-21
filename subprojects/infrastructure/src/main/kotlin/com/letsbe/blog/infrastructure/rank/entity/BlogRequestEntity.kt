package com.letsbe.blog.infrastructure.rank.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "blog_request_info")
data class BlogRequestEntity(
    @Id
    val keyword: String,
    val count: Int
)
