package com.letsbe.blog.applications

import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = [
        ApplicationsTestApplication::class
    ]
)
abstract class ApplicationsAbstractServiceTest
