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
//    implementation("org.springframework.cloud:spring-cloud-starter-config")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    runtimeOnly("com.h2database:h2")

    implementation("org.springframework.boot:spring-boot-starter-webflux")

    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j")
    implementation("org.springframework.cloud:spring-cloud-stream")
    // 2. Kafka 바인더 추가
    implementation("org.springframework.cloud:spring-cloud-stream-binder-kafka")
    implementation("io.github.resilience4j:resilience4j-feign")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.springframework.cloud", module = "spring-cloud-starter-config")
    }
}