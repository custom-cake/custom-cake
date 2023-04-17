import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.6.21"
    id("org.springframework.boot") version "2.7.9"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion

    // add querydsl plugin
    id("com.ewerk.gradle.plugins.querydsl") version "1.0.10"

    // kapt
    kotlin("kapt") version kotlinVersion
    // ✅ Intellij에서 사용할 파일을 생성하는 플러그인
    idea
}

val querydslVersion = "5.0.0"


group = "com.cake"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    apply(plugin = "kotlin-kapt")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-batch")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.batch:spring-batch-test")

    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    implementation("org.springframework.boot:spring-boot-starter-validation")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // https://mvnrepository.com/artifact/mysql/mysql-connector-java
    implementation("mysql:mysql-connector-java:8.0.25")
    // https://mvnrepository.com/artifact/org.hibernate/hibernate-core
    implementation("org.hibernate:hibernate-core:5.6.8.Final")
    // redis
    implementation("org.springframework.boot:spring-boot-starter-data-redis")

    // p6spy (운영 환경에서 사용하려면 성능 테스트 필수 !)
    implementation("com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.8.1")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")

    // querydsl library (version 명시 필요 - https://wangtak.tistory.com/m/44)
    implementation("com.querydsl:querydsl-jpa:$querydslVersion")
    // "~~:jpa" : for using JPAAnnotationProcessor
    kapt("com.querydsl:querydsl-apt:$querydslVersion:jpa")

    // configuration-processor
    kapt("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    // swagger
    implementation("io.springfox:springfox-boot-starter:3.0.0")

    // web socket
    implementation("org.springframework:spring-websocket:5.3.18")
}

// ✅ QClass를 Intellij가 사용할 수 있도록 경로에 추가
idea {
    module {
        val kaptMain = file("build/generated/source/kapt/main")
        sourceDirs.add(kaptMain)
        generatedSourceDirs.add(kaptMain)
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}