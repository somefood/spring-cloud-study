pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "spring-cloud-study"

include("gateway-service", "member-service", "config-service", "order-service", "product-service")