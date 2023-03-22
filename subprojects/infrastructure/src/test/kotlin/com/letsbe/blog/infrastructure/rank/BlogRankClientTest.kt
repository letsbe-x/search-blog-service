package com.letsbe.blog.infrastructure.rank

import com.letsbe.blog.domain.rank.dto.BlogRankItemDto
import com.letsbe.blog.domain.search.dto.BlogSearchRequestDto
import com.letsbe.blog.infrastructure.rank.entity.BlogRequestHistoryEntity
import com.letsbe.blog.infrastructure.rank.repository.BlogRequestHistoryInfoRepository
import com.letsbe.blog.infrastructure.rank.repository.RedisRepository
import junit.framework.TestCase.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyList
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.redis.RedisConnectionFailureException
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [BlogRankClient::class])
internal class BlogRankClientTest {

    @Autowired
    private lateinit var blogRankClient: BlogRankClient

    @MockBean
    private lateinit var redisRepository: RedisRepository

    @MockBean
    private lateinit var blogRequestHistoryInfoRepository: BlogRequestHistoryInfoRepository

    @Test
    fun `getBlogRequestRankTest - redis connection success`() {
        // given
        val expectedResult = listOf(
            BlogRankItemDto("keyword1", 10),
            BlogRankItemDto("keyword2", 5)
        )

        // when
        `when`(redisRepository.getBlogRequestItem()).thenReturn(expectedResult)

        // then
        val result = blogRankClient.getBlogRequestRank()
        assertEquals(expectedResult, result)
        verify(redisRepository, times(1)).getBlogRequestItem()
        verifyNoMoreInteractions(redisRepository)
        verify(blogRequestHistoryInfoRepository, times(0)).getAllByBlogRequestItem()
        verify(redisRepository, times(0)).initializeRankStore(anyList())
    }

    @Test
    fun `getBlogRequestRankTest - redis connection fail`() {
        // given
        // when
        `when`(redisRepository.getBlogRequestItem()).thenThrow(RedisConnectionFailureException("Redis connection error"))

        // then
        blogRankClient.getBlogRequestRank()

        // Verify the interactions with the mocks
        verify(redisRepository).getBlogRequestItem()
        verify(redisRepository, times(1)).initializeRankStore(anyList())
        verifyNoMoreInteractions(redisRepository)
        verify(blogRequestHistoryInfoRepository).getAllByBlogRequestItem()
        verifyNoMoreInteractions(blogRequestHistoryInfoRepository)
    }

    @Test
    fun `increaseCount test`() {
        // given
        val blogSearchRequestDto = BlogSearchRequestDto("test_query")
        val blogRequestHistoryEntity = BlogRequestHistoryEntity(1, "test_query", "kakao")

        // when
        `when`(blogRequestHistoryInfoRepository.save(blogRequestHistoryEntity)).thenReturn(blogRequestHistoryEntity)
        doNothing().`when`(redisRepository).increaseRequestCounter(blogSearchRequestDto.query)

        blogRankClient.increaseCount(blogSearchRequestDto)

        // then
        verify(redisRepository, times(1)).increaseRequestCounter(anyString())
        verify(blogRequestHistoryInfoRepository, times(1)).save(any<BlogRequestHistoryEntity>())
        verifyNoMoreInteractions(blogRequestHistoryInfoRepository)
        verify(redisRepository).increaseRequestCounter(blogSearchRequestDto.query)
        verifyNoMoreInteractions(redisRepository)
    }
}
