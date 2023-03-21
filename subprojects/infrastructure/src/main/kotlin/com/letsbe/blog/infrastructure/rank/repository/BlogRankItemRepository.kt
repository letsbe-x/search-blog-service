package com.letsbe.blog.infrastructure.rank.repository

import com.letsbe.blog.infrastructure.rank.entity.BlogRequestEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BlogRankItemRepository : JpaRepository<BlogRequestEntity, String> {
    fun findTop10ByOrderByCountDesc(): List<BlogRequestEntity>
    fun existsByKeyword(keyword: String): Boolean
    fun getBlogRequestEntityByKeyword(keyword: String): BlogRequestEntity?
}
