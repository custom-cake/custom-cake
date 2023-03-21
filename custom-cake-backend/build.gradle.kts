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
    // ✅ Intellij에서 사용할 파일을 생성하는 플러그인입니다
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

    runtimeOnly("com.h2database:h2")

    // p6spy (운영 환경에서 사용하려면 성능 테스트 필수 !)
    implementation("com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.8.1")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")

    // querydsl library (version 명시 필요 - https://wangtak.tistory.com/m/44)
    kapt("com.querydsl:querydsl-apt:$querydslVersion")
    implementation("com.querydsl:querydsl-jpa:$querydslVersion")

    // configuration-processor
    kapt("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
}

// ✅ QClass를 Intellij가 사용할 수 있도록 경로에 추가합니다
idea {
    module {
        val kaptMain = file("$buildDir/generated/source/kapt/main")
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

//tasks.getByName<Jar>("jar") {
//    enabled = false
//}