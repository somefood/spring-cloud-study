// config-service/build.gradle.kts
plugins {
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:2023.0.1")
    }
}

dependencies {
    // 1. Config Server 스타터
    implementation("org.springframework.cloud:spring-cloud-config-server")
    // 2. Config Server도 Eureka에 등록하여 고가용성 확보
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")

    // Kotlin dependencies
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}