package com.letsbe.blog.infrastructure.rank.entity

interface BlogRequestItem {
    fun getKeyword(): String
    fun getCount(): Int
}
