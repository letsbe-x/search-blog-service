package com.letsbe.search.blog.interfaces.controller

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired

class HelloController {
    @Autowired
    private val logger = LoggerFactory.getLogger(this::class.java)
}
