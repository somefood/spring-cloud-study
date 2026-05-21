// order-service/build.gradle.kts
plugins {
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
    kotlin("plugin.jpa") version "1.9.24"
}

springBoot {
    mainClass.set("com.ecommerce.order.OrderServiceApplicationKt")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:2023.0.1")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.cloud:spring-cloud-starter-config")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("org.springframework.cloud:spring-cloud-starter-consul-discovery")

    runtimeOnly("com.h2database:h2")

    implementation("org.springframework.boot:spring-boot-starter-webflux")

    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    // 1. Spring Cloud Circuit Breaker 스타터 (Resilience4j 구현체 포함)
    implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j")

    // 2. Feign과의 연동을 위해 필요
    implementation("io.github.resilience4j:resilience4j-feign")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.springframework.cloud", module = "spring-cloud-starter-config")
    }
}