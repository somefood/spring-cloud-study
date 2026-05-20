plugins {
    java
    id("org.springframework.boot") version "3.2.4" apply false
    id("io.spring.dependency-management") version "1.1.4" apply false
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

extra["springCloudVersion"] = "2023.0.1"
extra["kotlinLoggingVersion"] = "3.0.5"

subprojects {
    apply(plugin = "java")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    group = "com.ecommerce"
    version = "1.0.0"

    repositories {
        mavenCentral()
    }

    the<io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension>().apply {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${rootProject.extra["springCloudVersion"]}")
        }
    }

    dependencies {
        add("implementation", "io.github.microutils:kotlin-logging-jvm:${rootProject.extra["kotlinLoggingVersion"]}")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}