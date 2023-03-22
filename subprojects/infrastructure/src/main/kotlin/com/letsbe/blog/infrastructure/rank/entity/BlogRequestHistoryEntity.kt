package com.letsbe.blog.infrastructure.rank.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(
    name = "blog_request_history_info",
    indexes = [
        jakarta.persistence.Index(name = "idx_blog_request_history_info_keyword", columnList = "keyword"),
        jakarta.persistence.Index(name = "idx_blog_request_history_info_provider", columnList = "provider")
    ]
)
data class BlogRequestHistoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val keyword: String,
    val provider: String
)
