pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "spring-cloud-study"

include("gateway-service", "member-service")