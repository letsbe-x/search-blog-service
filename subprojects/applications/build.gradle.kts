tasks.bootJar.get().enabled = false

dependencies {
    implementation(project(":infrastructure"))
    implementation(project(":domain"))

    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
}
