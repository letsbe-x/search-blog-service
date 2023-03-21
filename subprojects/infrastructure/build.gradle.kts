tasks.bootJar.get().enabled = false

plugins {
    id("org.jetbrains.kotlin.plugin.noarg") version "1.8.10"
}

dependencies {
    implementation(project(":domain"))

    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor") {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter")
    }
    // TODO: 3년간 업데이트가 없는 embedded redis 라이브러리 / 괜찮아보이는 라이브러리 찾아보기
    implementation("it.ozimov:embedded-redis:0.7.3") {
        exclude(group = "org.slf4j", module = "slf4j-simple")
    }
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    runtimeOnly("com.h2database:h2:2.1.214")

    testImplementation("com.squareup.okhttp3:mockwebserver")
    testImplementation("io.projectreactor:reactor-test")
}

allOpen {
    annotation("jakarta.persistence.Entity")
}

noArg {
    annotation("jakarta.persistence.Entity")
}
