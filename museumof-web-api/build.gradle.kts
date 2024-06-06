tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

dependencies {
    implementation(project(":museumof-domain"))
    runtimeOnly(project(":storage:museumof-db"))

    implementation("org.springframework.boot:spring-boot-starter-web")
}