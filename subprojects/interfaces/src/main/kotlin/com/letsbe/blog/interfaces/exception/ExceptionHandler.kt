package com.letsbe.blog.interfaces.exception

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.reactive.function.client.WebClientResponseException
import org.springframework.web.reactive.function.client.WebClientResponseException.NotFound

@ControllerAdvice(value = ["com.letsbe.blog.interfaces"])
class ExceptionHandler {
    private val logger = LoggerFactory.getLogger(ExceptionHandler::class.java)

    @ExceptionHandler(WebClientResponseException::class)
    fun handleBadRequestException(ex: WebClientResponseException): ResponseEntity<Any> {
        val response = mapOf("error" to ex.message)
        logger.error(ex.message)
        return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(NotFound::class)
    fun handleBadRequestException(ex: NotFound): ResponseEntity<Any> {
        val response = mapOf("available route" to listOf("/blog/search?query", "/blog/rank"))
        logger.error(ex.message)
        return ResponseEntity(response, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(BlogServiceException::class)
    fun handleBlogServiceException(ex: BlogServiceException): ResponseEntity<Any> {
        val response = mapOf("error" to ex.message)
        logger.error(ex.message)
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(RuntimeException::class)
    fun handleBlogServiceException(ex: RuntimeException): ResponseEntity<Any> {
        val response = mapOf("error" to ex.message)
        logger.error(ex.message)
        return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}

class BlogServiceException(message: String) : RuntimeException(message)
