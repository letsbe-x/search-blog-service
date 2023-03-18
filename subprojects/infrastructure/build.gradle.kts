tasks.bootJar.get().enabled = false

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux") // TODO: Netty로 동작안하게 되므로 의존생 다시 생각
    implementation("org.springframework.boot:spring-boot-starter-web")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
}
