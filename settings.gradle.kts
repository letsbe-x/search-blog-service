rootProject.name = "search-blog-service"

includeProject(":infrastructure")
includeProject(":domain")
includeProject(":applications")
includeProject(":interfaces")

fun includeProject(name: String) {
    include(name)
    project(name).projectDir = file("subprojects/$name")
}

rootProject.children.forEach { project ->
    project.projectDir = file("subprojects/${project.name}")
    assert(project.projectDir.isDirectory)
}
