import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.3.4.RELEASE"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.4.20"
    kotlin("jvm") version "1.4.10"
    kotlin("plugin.spring") version "1.4.10"
}

group = "br.com.alura"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("de.codecentric:spring-boot-admin-starter-client:2.3.0")
    implementation("io.springfox:springfox-swagger2:2.9.2")
    implementation("io.springfox:springfox-swagger-ui:2.9.2")
    implementation("com.h2database:h2")
    runtimeOnly("org.springframework.boot:spring-boot-devtools")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.jsonwebtoken:jjwt:0.9.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude("junit", "junit")
        exclude("org.junit.vintage", "junit-vintage-engine")
    }


    testImplementation("org.junit.jupiter:junit-jupiter-api:5.2.0")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.2.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.2.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}