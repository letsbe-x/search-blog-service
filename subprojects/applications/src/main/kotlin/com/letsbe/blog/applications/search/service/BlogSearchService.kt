package com.letsbe.blog.applications.search.service

import com.letsbe.blog.applications.rank.service.BlogRankService
import com.letsbe.blog.applications.search.dto.BlogPostDto
import com.letsbe.blog.domain.search.aggregates.BlogSearchRequestDo
import com.letsbe.blog.infrastructure.search.external.BlogSearchClientService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class BlogSearchService(
    private val blogSearchClientService: BlogSearchClientService,
    private val blogRankService: BlogRankService
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    // TODO: 로직이 BlogSearchDO로 이동되어야 함
    suspend fun blogSearch(keyword: String, sort: String, page: Int, size: Int, provider: String): Flux<BlogPostDto> {
        val request = BlogSearchRequestDo(keyword, sort, page, size, provider).toDto()

        // TODO: 서비스 연계는 DO에서 관리해야합니다. / 서비스 연계는 비즈니스 로직이 아닙니다.
        withContext(Dispatchers.IO) {
            logger.info("keyword: {}", keyword)
            logger.info("Thread IO: {}", Thread.currentThread().name)
            blogRankService.updateRank(keyword)
        }

        return blogSearchClientService.search(request).map { BlogPostDto.from(it) }
    }
}
