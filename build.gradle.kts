@file:Suppress("PropertyName")

val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val exposedVersion: String by project

plugins {
    kotlin("jvm") version "1.8.10"
    id("io.ktor.plugin") version "2.2.3"
    id("co.uzzu.dotenv.gradle") version "2.0.0"
    id("com.github.gmazzo.buildconfig") version "3.1.0"
    kotlin("plugin.serialization") version "1.8.10"
}

group = "com.hello"
version = "0.0.1"

application {
    mainClass.set("com.hello.ApplicationKt")

    val isDevelopment: String = project.ext.get("development") as String
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-java-time:$exposedVersion")
    implementation("org.mariadb.jdbc:mariadb-java-client:3.1.2")
    implementation("io.ktor:ktor-server-openapi:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")

    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}

buildConfig {
    buildConfigField("String", "DB_USERNAME", env.DB_USERNAME.value.toBuildConfigString())
    buildConfigField("String", "DB_PASSWORD", env.DB_PASSWORD.value.toBuildConfigString())
}

fun String.escape() = replace("\$", "\\\$")

fun String.toBuildConfigString() = "\"${this.escape()}\""