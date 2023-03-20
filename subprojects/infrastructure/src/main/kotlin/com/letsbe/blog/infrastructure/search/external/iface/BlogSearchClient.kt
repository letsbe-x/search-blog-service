package com.letsbe.blog.infrastructure.search.external.iface

import com.letsbe.blog.domain.search.dto.BlogSearchRequestDto
import com.letsbe.blog.domain.search.dto.BlogSearchResultDto
import reactor.core.publisher.Flux

interface BlogSearchClient {
    suspend fun search(request: BlogSearchRequestDto): Flux<BlogSearchResultDto>
}
