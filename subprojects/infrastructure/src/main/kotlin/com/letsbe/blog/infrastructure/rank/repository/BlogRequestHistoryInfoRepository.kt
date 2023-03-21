package com.letsbe.blog.infrastructure.rank.repository

import com.letsbe.blog.infrastructure.rank.entity.BlogRequestHistoryEntity
import com.letsbe.blog.infrastructure.rank.entity.BlogRequestItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface BlogRequestHistoryInfoRepository : JpaRepository<BlogRequestHistoryEntity, String> {
    @Query("SELECT keyword, count(*) as count FROM blog_request_history_info group by keyword order by count desc", nativeQuery = true)
    fun getAllByBlogRequestItem(): List<BlogRequestItem>
}
