tasks.bootJar.get().enabled = false

dependencies {
    implementation(project(":domain"))

    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    testImplementation("com.squareup.okhttp3:mockwebserver")
}
