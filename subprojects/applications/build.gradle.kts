tasks.bootJar.get().enabled = false

dependencies {
    implementation(project(":infrastructure"))
    implementation("org.springframework:spring-context")
}
