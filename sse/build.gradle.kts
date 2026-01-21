extra["springAiVersion"] = "1.1.2"

dependencies {
    implementation("org.springframework.ai:spring-ai-starter-model-deepseek")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.ai:spring-ai-bom:${property("springAiVersion")}")
    }
}